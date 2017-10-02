package group;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class SQLitePlayground {
    public void withRecursive() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:sqlite::memory:");
                Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(30);

                stmt.executeUpdate("drop table if exists UserGroupExpand");
                stmt.executeUpdate("create table UserGroupExpand(groupid varchar(40), ingrouplist varchar(40))");
                stmt.executeUpdate("insert into UserGroupExpand values ('UG001', 'UG002')");
                stmt.executeUpdate("insert into UserGroupExpand values ('UG001', 'UG003')");
                stmt.executeUpdate("insert into UserGroupExpand values ('UG002', 'UG003')");
                stmt.executeUpdate("insert into UserGroupExpand values ('UG002', 'UG004')");
                stmt.executeUpdate("insert into UserGroupExpand values ('UG005', 'UG001')");

                ResultSet rs = stmt.executeQuery("with recursive connect_to(n) as ( values('UG001') UNION select ingrouplist from UserGroupExpand, connect_to where UserGroupExpand.groupid = connect_to.n ) select distinct ingrouplist from UserGroupExpand where UserGroupExpand.groupid in connect_to");
                while(rs.next()) {
                    System.out.println(rs.getString("ingrouplist"));
                }
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                if(conn != null) {
                    try {
                        conn.close();
                    } catch(SQLException e) {}
                }
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Set<String>> makeAdjList(String file) {
        Map<String, Set<String>> adjList = new HashMap<>();
        try {
            Files.lines(Paths.get(file)).forEach(item -> {
                String kv[] = item.trim().split(",");
                if (kv.length == 2) {
                    if(!adjList.containsKey(kv[0])) {
                        adjList.put(kv[0], new HashSet<>());
                    }
                    adjList.get(kv[0]).add(kv[1]);
                }
            });
        } catch(IOException e) {
            e.printStackTrace();
        }
        // print AdjList
//        adjList.forEach((k, v) -> {
//            System.out.print(k + ": " + v.size());
//            //v.forEach(item -> System.out.print(v + " "));
//            System.out.println();
//        });
        return adjList;
    }

    private void recursiveAdjList(Map<String, Set<String>> adjList, String start, Set<String> visited, Set<String> collector) {
        visited.add(start);
        collector.add(start);
        if(!adjList.containsKey(start) || adjList.get(start).size() == 0) {
            return;
        }
        adjList.get(start).forEach(id -> {
            if(!visited.contains(id)) {
                recursiveAdjList(adjList, id, visited, collector);
            }
        });
    }

    public Set<String> walkThroughAdjList(Map<String, Set<String>> adjList, String start) {
        Set<String> visited = new HashSet<>();
        Set<String> result = new HashSet<>();

        if(adjList.containsKey(start)) {
            visited.add(start);
            adjList.get(start).forEach(id -> {
                if(!visited.contains(id)) {
                    recursiveAdjList(adjList, id, visited, result);
                }
            });
        }
        return Collections.unmodifiableSet(result);
    }

    public void testJarvisDataAdd(int initSize, String saveTo) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:sqlite::memory:");
                Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(30);

                stmt.executeUpdate("drop table if exists UserGroupExpand");
                stmt.executeUpdate("create table UserGroupExpand(groupid varchar(40), ingrouplist varchar(40))");
                Files.lines(Paths.get("/Users/tzuyichao/Downloads/jarvis")).forEach(item -> {
                    String kv[] = item.trim().split(",");
                    if (kv.length == 2) {
                        try {
                            stmt.executeUpdate("insert into UserGroupExpand values ('" + kv[0] + "', '" + kv[1] + "')");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                // generate more node
                Set<String> seeds = new HashSet<>();
                Set<String> newNodes = new HashSet<>();
                ResultSet rs = stmt.executeQuery("select distinct ingrouplist from UserGroupExpand limit " + initSize);
                while(rs.next()) {
                    seeds.add(rs.getString("ingrouplist"));
                }
                for(int level = 0; level < 50; level++) {
                    newNodes.clear();
                    for(String seed: seeds) {
                        String node = UUID.randomUUID().toString();
                        try {
                            stmt.executeUpdate("insert into UserGroupExpand values ('" + seed + "', '" + node + "')");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        newNodes.add(node);
                    }
                    seeds.clear();
                    seeds.addAll(newNodes);
                }
                List<Long> data = new ArrayList();
                for (int i=0; i<10; i++) {
                    long start = System.currentTimeMillis();
                    rs = stmt.executeQuery("with recursive connect_to(n) as ( values('be79ddc4-fd40-45a6-bdd2-5a5732d49985') UNION select ingrouplist from UserGroupExpand, connect_to where UserGroupExpand.groupid = connect_to.n ) select ingrouplist from UserGroupExpand where UserGroupExpand.groupid in connect_to");
                    long timeElapse = System.currentTimeMillis() - start;
                    data.add(timeElapse);
                    System.out.println("Time Elapse: " + timeElapse);
                    int count = 0;
                    while (rs.next()) {
                        count++;
                        //System.out.println(rs.getString("ingrouplist"));
                    }
                    System.out.println("Row Count: " + count);
                }
                //System.out.println(data);
                printStat(data);

                // save to file
                Path saveToPath = Paths.get("/Users/tzuyichao/Downloads/" + saveTo);
                //File saveToFile = saveToPath.toFile();
                //if(saveToFile.isFile() && !saveToFile.exists()) {
                    rs = stmt.executeQuery("select groupid, ingrouplist from UserGroupExpand");
                    StringBuilder result = new StringBuilder();
                    while(rs.next()) {
                        result.append(String.format("%s,%s\n", rs.getString("groupid"), rs.getString("ingrouplist")));
                    }
                    try (BufferedWriter writer = Files.newBufferedWriter(saveToPath, Charset.forName("UTF-8"))){
                        writer.write(result.toString());
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Write Complete.");
                //}
            } catch(IOException | SQLException e) {
                e.printStackTrace();
            } finally {
                if(conn != null) {
                    try {
                        conn.close();
                    } catch(SQLException e) {}
                }
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testJarvisDataNormal() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:sqlite::memory:");
                Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(30);

                stmt.executeUpdate("drop table if exists UserGroupExpand");
                stmt.executeUpdate("create table UserGroupExpand(groupid varchar(40), ingrouplist varchar(40))");
                Files.lines(Paths.get("/Users/tzuyichao/Downloads/jarvis")).forEach(item -> {
                    String kv[] = item.trim().split(",");
                    if (kv.length == 2) {
                        try {
                            stmt.executeUpdate("insert into UserGroupExpand values ('" + kv[0] + "', '" + kv[1] + "')");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });

                List<Long> data = new ArrayList();
                for (int i=0; i<10; i++) {
                    long start = System.currentTimeMillis();
                    ResultSet rs = stmt.executeQuery("with recursive connect_to(n) as ( values('be79ddc4-fd40-45a6-bdd2-5a5732d49985') UNION select ingrouplist from UserGroupExpand, connect_to where UserGroupExpand.groupid = connect_to.n ) select ingrouplist from UserGroupExpand where UserGroupExpand.groupid in connect_to");
                    long timeElapse = System.currentTimeMillis() - start;
                    data.add(timeElapse);
                    System.out.println("Time Elapse: " + timeElapse);
                    int count = 0;
                    while (rs.next()) {
                        count++;
                        //System.out.println(rs.getString("ingrouplist"));
                    }
                    System.out.println("Row Count: " + count);
                }
                //System.out.println(data);
                printStat(data);
            } catch(IOException | SQLException e) {
                e.printStackTrace();
            } finally {
                if(conn != null) {
                    try {
                        conn.close();
                    } catch(SQLException e) {}
                }
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printStat(Collection<Long> data) {
        Integer max = data.stream().mapToInt(i -> i.intValue()).max().getAsInt();
        Integer min = data.stream().mapToInt(i -> i.intValue()).min().getAsInt();
        double avg = data.stream().mapToLong(Long::longValue).sum()/(double)data.size();
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Avg: " + avg);
    }

    public static void main(String[] args) {
        SQLitePlayground playground = new SQLitePlayground();
        //playground.testJarvisDataNormal();
        //playground.testJarvisDataAdd(4000, "jarvis4000");

        Map<String, Set<String>> adjList = playground.makeAdjList("/Users/tzuyichao/Downloads/jarvis5000");
        List<Long> data = new ArrayList();
        for (int i=0; i<10; i++) {
            long start = System.currentTimeMillis();
            Set<String> result = playground.walkThroughAdjList(adjList, "be79ddc4-fd40-45a6-bdd2-5a5732d49985");
            System.out.println("Total: " + result.size());
            long diff = (System.currentTimeMillis() - start);
            System.out.println("Time: " + diff);
            data.add(diff);
        }
        printStat(data);
    }
}
