package net.dev.squery.builders;

import java.util.HashMap;
import java.util.Map;

public abstract class QueryBuilder<T> {
    protected StringBuilder query;
    protected Map<String, Object> parameters;
    protected boolean hasCondition;

    public QueryBuilder() {
        query = new StringBuilder();
        parameters = new HashMap<>();
        hasCondition = false;
    }

    public abstract QueryBuilder<T> withEntity(T entity);

    public abstract QueryBuilder<T> forTable(String table);

    public abstract QueryBuilder<T> forId(int id);

    public abstract QueryBuilder<T> findAll();

    public abstract QueryBuilder<T> where(String condition);

    public abstract String build();

    public Map<String, Object> getParameters() {
        return parameters;
    }
}

