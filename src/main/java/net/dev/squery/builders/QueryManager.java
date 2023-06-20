package net.dev.squery.builders;

import net.dev.squery.queries.CreateQuery;
import net.dev.squery.queries.DeleteQuery;
import net.dev.squery.queries.SelectQuery;
import net.dev.squery.queries.UpdateQuery;

public class QueryManager<T> {
    public static <T> CreateQuery<T> createQuery() {
        return new CreateQuery<>();
    }

    public static <T> SelectQuery<T> selectQuery() {
        return new SelectQuery<>();
    }

    public static <T> UpdateQuery<T> updateQuery() {
        return new UpdateQuery<>();
    }

    public static <T> DeleteQuery<T> deleteQuery() {
        return new DeleteQuery<>();
    }
}
