package akula.factory.annotations;

import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;
import akula.factory.DuplicatePersistentException;
import akula.factory.PersistenceHandler;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;

public class FactoryPersistentAnnotationListener implements ClassAnnotationDiscoveryListener {

    private AtomicReference<Class> factoryPersistent;

    public FactoryPersistentAnnotationListener(AtomicReference<Class> factoryPersistent) {
        this.factoryPersistent = factoryPersistent;
    }

    public String[] supportedAnnotations() {
        return new String[] {
            PersistenceHandler.class.getName()
        };
    }

    public void discovered(String persistentClass, String annotationName) {
        try {
            Class factoryPersistentClass = Class.forName(persistentClass);
            PersistenceHandler persistenceHandlerAnnotation = (PersistenceHandler) factoryPersistentClass.getAnnotation(Class.forName(annotationName));
            if (factoryPersistent.get() != null)
                throw new DuplicatePersistentException(format("Duplicate persistent listeners between (%s) and (%s). There can be only one defined.", factoryPersistentClass.getCanonicalName(), persistenceHandlerAnnotation.annotationType().getCanonicalName()));
            factoryPersistent.set(factoryPersistentClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
