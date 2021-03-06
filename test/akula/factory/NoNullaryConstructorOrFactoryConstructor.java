package akula.factory;

import org.junit.Test;

class ClassWithoutNullaryConstructorNorFactoryConstructor {

    public ClassWithoutNullaryConstructorNorFactoryConstructor(int foo) {
    }
}

@Factory(ClassWithoutNullaryConstructorNorFactoryConstructor.class)
class ClassWithoutNullaryConstructorNorFactoryConstructorSetup {

    public void foo(Object something) {
    }
}

public class NoNullaryConstructorOrFactoryConstructor {

    @Test(expected = FactoryInstantiationException.class)
    public void shouldThrowExceptionForClassWithoutNullaryConstructorNorFactoryConstructor() {
        Instantiator.create(ClassWithoutNullaryConstructorNorFactoryConstructor.class);
    }
}
