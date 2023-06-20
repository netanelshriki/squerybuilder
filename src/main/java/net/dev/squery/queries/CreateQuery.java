package net.dev.squery.queries;

import net.dev.squery.builders.QueryBuilder;

import java.lang.reflect.Field;

public class CreateQuery<T> extends QueryBuilder<T> {
    private T entity;
    private String table;

    public CreateQuery<T> withEntity(T entity) {
        this.entity = entity;
        return this;
    }

    public CreateQuery<T> fromTable(String table) {
        query.append("INSERT INTO ").append(table).append(" ");
        this.table = table;
        return this;
    }

    @Override
    public QueryBuilder<T> forId(int id) {
        return null;
    }

    @Override
    public QueryBuilder<T> findAll() {
        return null;
    }

    @Override
    public QueryBuilder<T> where(String condition) {
        return null;
    }

    public String build() {
        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();

        query.append("(");
        StringBuilder values = new StringBuilder("VALUES (");
        boolean firstColumn = true;

        // Extract values from the entity and construct the query
        for (Field field : fields) {
            field.setAccessible(true);
            String columnName = field.getName();
            Object value;
            try {
                value = field.get(entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing entity properties", e);
            }

            if (!firstColumn) {
                query.append(", ");
                values.append(", ");
            }

            query.append(columnName);
            values.append(value instanceof String ? "'" + value + "'" : value);
            firstColumn = false;
        }

        query.append(") ").append(values).append(")");

        return query.toString();
    }
}

