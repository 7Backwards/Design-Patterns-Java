package com.es2.designpatterns.cargo;

import com.es2.designpatterns.exceptions.ContainerNotFoundException;
import com.es2.designpatterns.exceptions.ContainerPoolMaxedOutException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ContainerReusablePoolTest {

    @Test
    void testaddContainer() throws ContainerNotFoundException, ContainerPoolMaxedOutException {
        ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 30);
    }

    @Test
    void testContainerNotFoundException() {

        assertThrows(ContainerNotFoundException.class,
                () -> {

                    ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 30);
                    Container container = ContainerReusablePool.getInstance().getContainer("NonExistName");
                });
    }

    @Test
    void testContainerPoolMaxedOutException() {

        assertThrows(ContainerPoolMaxedOutException.class,
                () -> {

                    ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
                    ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
                    ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
                    ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
                });
    }

    @Test
    void testsetnameContainer() throws ContainerNotFoundException, ContainerPoolMaxedOutException {

        ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
        Container container = ContainerReusablePool.getInstance().getContainer("Contentor");
        container.setName("teste");
        ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "teste");
    }

    @Test
    void testreleaseContainer() {

        assertThrows(ContainerNotFoundException.class,
                () -> {

                    ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 10);
                    Container container = ContainerReusablePool.getInstance().getContainer("Contentor");
                    container.setName("Caixa3");
                    ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "Caixa3");
                    ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "Caixa3");
                });
    }
}