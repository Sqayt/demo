/*
 * This file is generated by jOOQ.
 */
package ru.ivanovds.tasks.demo.entity;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import ru.ivanovds.tasks.demo.entity.tables.Databasechangelog;
import ru.ivanovds.tasks.demo.entity.tables.Databasechangeloglock;
import ru.ivanovds.tasks.demo.entity.tables.Person;
import ru.ivanovds.tasks.demo.entity.tables.Task;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.databasechangelog</code>.
     */
    public final Databasechangelog DATABASECHANGELOG = Databasechangelog.DATABASECHANGELOG;

    /**
     * The table <code>public.databasechangeloglock</code>.
     */
    public final Databasechangeloglock DATABASECHANGELOGLOCK = Databasechangeloglock.DATABASECHANGELOGLOCK;

    /**
     * The table <code>public.person</code>.
     */
    public final Person PERSON = Person.PERSON;

    /**
     * The table <code>public.task</code>.
     */
    public final Task TASK = Task.TASK;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Databasechangelog.DATABASECHANGELOG,
            Databasechangeloglock.DATABASECHANGELOGLOCK,
            Person.PERSON,
            Task.TASK
        );
    }
}
