package group;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class TestGroup {
    @Test
    public void test() throws Exception {
        DepGroup root = new DepGroup();

        DepGroup dep1 = new DepGroup();
        dep1.setParent(root);

        UserGroup ug1 = new UserGroup();
        ug1.setParent(dep1);

        UserGroup ugr = new UserGroup();
        ugr.setParent(root);

        assertEquals(root, ugr.getParent());
        assertEquals(1, dep1.getChildren().size());
    }

    @Test
    public void test2() {
        DepGroup root = new DepGroup();

        UserGroup ugr = new UserGroup();
        try {
            root.setParent(ugr);
        } catch(IllegalParentTypeException e) {
            System.out.println("Good");
            return;
        }
        fail("Not go here");
    }

//    @Test
//    public void test3() {
//        Group g1 = new UserGroup();
//        Group g2 = new DepGroup();
//        Group g3 = new ProjectGroup();
//
//        assertTrue(g1 instanceof UserGroup);
//        assertTrue(g1 instanceof Group);
//        assertTrue(g2 instanceof DepGroup);
//        assertTrue(g2 instanceof Group);
//        assertTrue(g3 instanceof ProjectGroup);
//        assertTrue(g3 instanceof Group);
//    }

    @Test
    public void invocation_test() throws Exception {
        Method setNameMth = UserGroup.class.getMethod("setName", String.class);
        UserGroup g1 = new UserGroup();
        setNameMth.invoke(g1, "Test");
        assertEquals("Test", g1.getName());
        System.out.println(g1.toString());
    }
}