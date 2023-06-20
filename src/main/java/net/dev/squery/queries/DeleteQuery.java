package net.dev.squery.queries;

import net.dev.squery.builders.QueryBuilder;

public class DeleteQuery<T> extends QueryBuilder<T> {
    private String table;
    private StringBuilder conditionBuilder;
    private boolean hasCondition;

    @Override
    public QueryBuilder<T> withEntity(T entity) {
        return null;
    }

    public DeleteQuery<T> forTable(String table) {
        this.table = table;
        return this;
    }

    public DeleteQuery<T> forId(int id) {
        if (!hasCondition) {
            conditionBuilder = new StringBuilder("WHERE ");
            hasCondition = true;
        } else {
            conditionBuilder.append("AND ");
        }
        conditionBuilder.append("id = ").append(id).append(" ");
        return this;
    }

    @Override
    public QueryBuilder<T> findAll() {
        return null;
    }

    public DeleteQuery<T> where(String condition) {
        if (!hasCondition) {
            conditionBuilder = new StringBuilder("WHERE ");
            hasCondition = true;
        } else {
            conditionBuilder.append("AND ");
        }
        conditionBuilder.append(condition).append(" ");
        return this;
    }

    public DeleteQuery<T> deleteAll() {
        return this;
    }

    public String build() {
        StringBuilder deleteQuery = new StringBuilder("DELETE FROM ").append(table);

        if (hasCondition) {
            deleteQuery.append(" ").append(conditionBuilder.toString());
        }

        return deleteQuery.toString();
    }
}


