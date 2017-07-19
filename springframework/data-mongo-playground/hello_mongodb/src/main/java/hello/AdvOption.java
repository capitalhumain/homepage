package hello;

import org.springframework.data.annotation.Id;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tzuyichao on 17/07/2017.
 */
public class AdvOption {
    @Id
    private String id;
    private String module;
    private String section;
    private BitSet privilege = new BitSet(5);
    private Map<String, List<AdvOptionSchema>> schemas = new HashMap<>();

    public AdvOption() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Map<String, List<AdvOptionSchema>> getSchemas() {
        return schemas;
    }

    public void setSchemas(Map<String, List<AdvOptionSchema>> schemas) {
        this.schemas = schemas;
    }

    public void setPrivilege(BitSet privilege) {
        this.privilege = privilege;
    }

    public BitSet getPrivilege() {
        return privilege;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(String.format("Id: %s\n", id));
        ret.append(String.format("Module: %s\n", module));
        ret.append(String.format("Section: %s\n", section));
        ret.append(String.format("Privilege: %s\n", privilege.toString()));
        for(String key : schemas.keySet()) {
            ret.append(String.format("key: %s\n", key));
            for(AdvOptionSchema schema : schemas.get(key)) {
                ret.append(schema.toString());
            }
        }

        return ret.toString();
    }
}
