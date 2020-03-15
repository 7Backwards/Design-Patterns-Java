package com.es2.designpatterns;

import com.es2.designpatterns.exceptions.ContainerNotFoundException;
import com.es2.designpatterns.exceptions.ContainerPoolMaxedOutException;

import java.util.ArrayList;

public class ContainerReusablePool {

    private static ContainerReusablePool instance;
    private Integer maxContentores;
    private Integer maxCaixas;
    private Integer maxEmbalagens;

    private ArrayList<Container> freeContentores;
    private ArrayList<Container> usedContentores;
    private ArrayList<Container> freeCaixas;
    private ArrayList<Container> usedCaixas;
    private ArrayList<Container> freeEmbalagens;
    private ArrayList<Container> usedEmbalagens;


    /**
     *  Container reusable pool constructor
     */
    private ContainerReusablePool() {

        this.maxContentores = 1;
        this.maxCaixas = 10;
        this.maxEmbalagens = 100;
        // Creates pools
        this.freeContentores = new ArrayList<>();
        this.usedContentores = new ArrayList<>();
        this.freeCaixas = new ArrayList<>();
        this.usedCaixas = new ArrayList<>();
        this.freeEmbalagens = new ArrayList<>();
        this.usedEmbalagens = new ArrayList<>();
    }

    /**
     *
     * @return Container Pool instance
     */
    public static ContainerReusablePool getInstance() {

        if (instance == null) {
            instance = new ContainerReusablePool();
        }
        return instance;
    }

    /**
     *
     * @param type Only Container type accepted for now
     * @param nome Container Name
     * @param size Container Size
     * @throws ContainerPoolMaxedOutException Max container number achieved
     * @throws ContainerNotFoundException Container type not found
     */
    public synchronized void addContainer(String type, String nome, int size) throws ContainerPoolMaxedOutException, ContainerNotFoundException {

        Container tempC;
        if(type.equals("Container")) {
            switch (nome) {
                case "Contentor":
                    if (freeContentores.size() + usedContentores.size() < maxContentores) {
                        tempC = new Container();
                        tempC.setName(nome);
                        tempC.setSize(size);
                        freeContentores.add(tempC);
                    }
                    break;
                case "Caixa":
                    if (freeCaixas.size() + usedCaixas.size() < maxCaixas) {
                        tempC = new Container();
                        tempC.setName(nome);
                        tempC.setSize(size);
                        freeCaixas.add(tempC);
                    }
                    break;
                case "Embalagem":
                    if (freeEmbalagens.size() + freeEmbalagens.size() < maxEmbalagens) {
                        tempC = new Container();
                        tempC.setName(nome);
                        tempC.setSize(size);
                        freeEmbalagens.add(tempC);
                    }
                    break;
                default:
                    throw new ContainerPoolMaxedOutException();
            }
        }
        else
            throw new ContainerNotFoundException();
    }


    /**
     *
     * @param containerTypeName Container Name
     * @return return container if available
     * @throws ContainerNotFoundException Exception if container name not found or no containers available
     */
    public synchronized Container getContainer(String containerTypeName) throws ContainerNotFoundException {

        Container tempC;
        switch (containerTypeName) {
            case "Contentor":
                tempC = getContainerByType(usedContentores, freeContentores, maxContentores);
                if(tempC != null)
                    return tempC;
                else
                    throw  new ContainerNotFoundException();
            case "Caixa":
                tempC = getContainerByType(usedCaixas, freeCaixas, maxCaixas);
                if(tempC != null)
                    return tempC;
                else
                    throw  new ContainerNotFoundException();
            case "Embalagem":
                tempC = getContainerByType(usedEmbalagens, freeEmbalagens, maxEmbalagens);
                if(tempC != null)
                    return tempC;
                else
                    throw  new ContainerNotFoundException();
            default:
                throw new ContainerNotFoundException();
        }
    }

    /**
     *
     * @param usedArray Unavailable Container Array
     * @param freeArray Available Container Array
     * @param MaxSize Array max size
     * @return Available container or Null
     */
    private synchronized Container getContainerByType(ArrayList<Container> usedArray, ArrayList<Container> freeArray, int MaxSize) {
        if(usedArray.size() >= MaxSize)
            return null;
        else if (freeArray.size() > 0) {
            Container tempC = freeArray.get(0);
            freeArray.remove(0);
            usedArray.add(tempC);
            return tempC;
        }
        else
            return null;
    }

    public synchronized void releaseContainerByName(String containerTypeName, String containerName) throws ContainerNotFoundException {

        switch (containerTypeName) {
            case "Contentor":
                for(Container container : usedContentores) {
                    if (container.getName().equals(containerName)) {
                        freeContentores.add(container);
                        usedContentores.remove(container);
                        return;
                    }
                }
                throw  new ContainerNotFoundException();
            case "Caixa":
                for(Container container : usedCaixas) {
                    if (container.getName().equals(containerName)) {
                        freeCaixas.add(container);
                        usedCaixas.remove(container);
                        return;
                    }
                }
                throw  new ContainerNotFoundException();
            case "Embalagem":
                for(Container container : usedEmbalagens) {
                    if (container.getName().equals(containerName)) {
                        freeEmbalagens.add(container);
                        usedEmbalagens.remove(container);
                        return;
                    }
                }
                throw  new ContainerNotFoundException();
            default:
                throw new ContainerNotFoundException();
        }
    }
}
