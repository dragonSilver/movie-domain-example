package net.dg.configuration;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;

import net.dg.Application;
import net.dg.domain.movie.Customer;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.Target;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = Application.class)
public class JpaSchemaExport {

    final String PROPERTY_NAME_DIALECT = "hibernate.dialect";
    final String PROPERTY_VALUE_DIALECT = "org.hibernate.dialect.H2Dialect";
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * 전체 Entity
     */
    @Test
    public void executeExport() {
        JpaSchemaExport export = new JpaSchemaExport();
        Configuration configuration = initConfigration(new ExportConfiguration() {
            @Override
            public List<Class<?>> findEntities() {
                List<Class<?>> entities = Lists.newArrayList();
                final Set<ManagedType<?>> managedTypes = entityManagerFactory.getMetamodel().getManagedTypes();
                for (ManagedType<?> managedType : managedTypes) {
                    entities.add(managedType.getJavaType());
                }
                return entities;
            }
        });
        export.execute(configuration);
    }

    /**
     * 특정 Entity들에 대한 스키마를 출력한다.
     * FK
     */
    @Test
    public void executeCustomExport() {
        JpaSchemaExport export = new JpaSchemaExport();
        Configuration configuration = initConfigration(new ExportConfiguration() {
            @Override
            public List<Class<?>> findEntities() {
                List<Class<?>> entities = Lists.newArrayList();
                entities.add(Customer.class);
                return entities;
            }
        });
        export.execute(configuration);
    }

    private void execute(Configuration configuration) {
        final String delimiter = ";";
        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.setDelimiter(delimiter);
        schemaExport.setFormat(true);
        schemaExport.setOutputFile("export.sql");
        schemaExport.execute(Target.SCRIPT, SchemaExport.Type.CREATE);
    }

    private Configuration initConfigration(ExportConfiguration exportConfiguration) {
        Configuration configuration = new Configuration();
        configuration.setProperty(PROPERTY_NAME_DIALECT, PROPERTY_VALUE_DIALECT);
        List<Class<?>> entities = exportConfiguration.findEntities();
        for (Class<?> annotatedClass : entities) {
            configuration.addAnnotatedClass(annotatedClass);
        }
        return configuration;
    }
    public static interface ExportConfiguration {

        List<Class<?>> findEntities();
    }
}
