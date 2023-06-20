package net.dev.squery.queries;

import net.dev.squery.builders.QueryBuilder;

public class SelectQuery<T> extends QueryBuilder<T> {
    private String table;
    private int id;
    private StringBuilder conditionBuilder;
    private boolean hasCondition;

    @Override
    public QueryBuilder<T> withEntity(T entity) {
        return null;
    }

    public SelectQuery<T> fromTable(String table) {
        this.table = table;
        return this;
    }

    public SelectQuery<T> forId(int id) {
        this.id = id;
        return this;
    }

    public SelectQuery<T> findAll() {
        return this;
    }

    public SelectQuery<T> where(String condition) {
        if (!hasCondition) {
            conditionBuilder = new StringBuilder();
            hasCondition = true;
        } else {
            conditionBuilder.append("AND ");
        }
        conditionBuilder.append(condition).append(" ");
        return this;
    }

    public String build() {
        StringBuilder selectQuery = new StringBuilder("SELECT * FROM ").append(table);

        if (id != 0) {
            selectQuery.append(" WHERE id = ").append(id);
        }

        if (hasCondition) {
            if (id == 0) {
                selectQuery.append(" WHERE ").append(conditionBuilder.toString());
            } else {
                selectQuery.append(" ").append(conditionBuilder.toString());
            }
        }
        return selectQuery.toString();
    }
}

