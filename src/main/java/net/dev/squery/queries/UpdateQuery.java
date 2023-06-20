package net.dev.squery.queries;

import net.dev.squery.builders.QueryBuilder;

import java.lang.reflect.Field;

public class UpdateQuery<T> extends QueryBuilder<T> {
    private String table;
    private T entity;
    private StringBuilder conditionBuilder;
    private boolean hasCondition;

    public UpdateQuery<T> forTable(String table) {
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

    public UpdateQuery<T> withEntity(T entity) {
        this.entity = entity;
        return this;
    }

    public UpdateQuery<T> where(String condition) {
        if (!hasCondition) {
            conditionBuilder = new StringBuilder("WHERE ");
            hasCondition = true;
        } else {
            conditionBuilder.append("AND ");
        }
        conditionBuilder.append(condition).append(" ");
        return this;
    }

    public String build() {
        StringBuilder updateQuery = new StringBuilder("UPDATE ").append(table).append(" SET ");

        // Extract values from the entity and construct the update query
        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();
        boolean firstColumn = true;

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
                updateQuery.append(", ");
            }

            updateQuery.append(columnName).append(" = ");
            if (value instanceof String) {
                updateQuery.append("'").append(value).append("'");
            } else {
                updateQuery.append(value);
            }

            firstColumn = false;
        }

        if (hasCondition) {
            updateQuery.append(" ").append(conditionBuilder.toString());
        }

        return updateQuery.toString();
    }
}
