package pl.sages.consumenewsapi.api.domain;

import java.io.Serializable;

public class Source implements Serializable {
    private Object id;
    private String name;
    private final static long serialVersionUID = -5174370131125620839L;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
