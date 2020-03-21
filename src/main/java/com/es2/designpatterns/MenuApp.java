package com.es2.designpatterns;


import com.es2.designpatterns.cargo.Container;
import com.es2.designpatterns.cargo.ContainerReusablePool;
import com.es2.designpatterns.cargo.Medicamento;
import com.es2.designpatterns.exceptions.*;
import com.es2.designpatterns.invoice.EmiteFatura;
import com.es2.designpatterns.invoice.FaturaCarga;
import com.es2.designpatterns.invoice.FaturaCargaPeriodoEspecial;
import com.es2.designpatterns.invoice.FaturaCargaPeriodoNormal;
import com.es2.designpatterns.users.UserManager;

import java.util.Scanner;


/**
 * MenuApp class
 * <p>
 * This class corresponds to the first menu that the user get
 * this class is used to choose between create and login an account
 * after that is used to display all the options according to the type of user
 *
 **/


class MenuApp {

    private static final String NULL = null;
    private Scanner pause = new Scanner(System.in); //scanned use to pause the system in order to avoid loops


    MenuApp() throws UserNotFoundException, ContainerNotFoundException, ContainerPoolMaxedOutException, UserTypeNotFoundException, UserExistingException {


        int userChoice = WelcomeMenu();

        while (userChoice != 0) {

            //switch case used in order to prevent other functions

            switch (userChoice) {
                case 1:
                    linhas(2);
                    int confir = LoginMenu();

                    break;

                case 2:
                    String registo;
                    registo = RegisterMenu();
                    System.out.println(registo);
                    pause.nextLine();

                    if ("Registo com sucesso".equals(registo)) {
                        new MenuApp();
                    }
                    break;

                default:
                    System.out.println("invalid character");

            }


        }
    }


    /**
     * function to display the main menu in order to choose the options
     *
     * @return the selection in the menu
     */


    private int WelcomeMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("##############################################################");
        System.out.println("######                                                 #######");
        System.out.println("######             WELCOME TO OUR MANAGE APP           #######");
        System.out.println("######         The best app to manage your orders      #######");
        System.out.println("######                                                 #######");
        System.out.println("######                                                 #######");
        System.out.println("######                                                 #######");
        System.out.println("######                    MENU                         #######");
        System.out.println("######                1 - LOGIN                        #######");
        System.out.println("######                2 - REGISTER                     #######");
        System.out.println("######                                                 #######");
        System.out.println("##############################################################");

        selection = input.nextInt();
        return selection;
    }


    /**
     * Function that correspond to the Login menu
     *
     * @return
     * @throws UserNotFoundException
     */


    private int LoginMenu() throws UserNotFoundException, ContainerNotFoundException, ContainerPoolMaxedOutException, UserTypeNotFoundException, UserExistingException {

        String Login;
        String password;


        Scanner input = new Scanner(System.in);


        System.out.println("##############################################################");
        System.out.println("######                                                 #######");
        System.out.println("######                    LOGIN                        #######");
        System.out.println("######         The best app to manage your orders      #######");
        System.out.println("######                                                 #######");
        System.out.println("######         Type Exit to Go to the main menu        #######");
        System.out.println("######                                                 #######");
        System.out.println("##############################################################");

        System.out.println("Username:");
        Login = input.nextLine();

        if ("Exit".equals(Login)) {
            new MenuApp();
        }


        System.out.println("Password:");
        password = input.nextLine();

        if ("Exit".equals(password)) {
            new MenuApp();
        }


        // IF  TO CHECK IF ITS "GESTOR" OR Drive and used to login

        try {
            if (UserManager.getInstanceLogin().loginUser(Login, password) && "admin".equals(Login)) {
                MenuAdmin();
            } else {
                MenuMotorista(Login);
            }
        } catch (UserNotFoundException e) {
            System.out.println("Password or Username don't match, Try Again!!!");
        } catch (InvalidPercentagemException e) {
            e.printStackTrace();
        } catch (FaturaCargaNotFoundException e) {
            e.printStackTrace();
        } catch (ContainerFullException e) {
            e.printStackTrace();
        }


        return 4;
    }


    /**
     * this menu is used to create an user (being driver or admin("gestor"))
     *
     * @return
     * @throws UserNotFoundException
     */

    private String RegisterMenu() throws UserNotFoundException, UserTypeNotFoundException, UserExistingException, ContainerNotFoundException, ContainerPoolMaxedOutException {

        String mensagem_invalidacao = "Error creating user!! Try again";
        String username;
        String password;
        String type;

        Scanner input = new Scanner(System.in);


        System.out.println("##############################################################");
        System.out.println("######                                                 #######");
        System.out.println("######                    REGISTER                     #######");
        System.out.println("######         The best app to manage your orders      #######");
        System.out.println("######                                                 #######");
        System.out.println("######           Type='Gestor' or 'Motorista'          #######");
        System.out.println("######         Type Exit to Go to the main menu        #######");
        System.out.println("##############################################################");

        System.out.println("Username:");
        username = input.nextLine();
        if ("Exit".equals(username)) {
            new MenuApp();
            mensagem_invalidacao = "A sair";
        }

        System.out.println("Password:");
        password = input.nextLine();

        if ("Exit".equals(password)) {
            new MenuApp();
            mensagem_invalidacao = "Exiting";
        }


        System.out.println("Type:");
        type = input.nextLine();


        try {
            return UserManager.getInstanceLogin().registerUser(type, username, password);
        } catch (UserExistingException e) {
            System.out.println("User already Created!!!");
        } catch (UserTypeNotFoundException e) {
            System.out.println("Type of User not Found");
        }

        return mensagem_invalidacao;

    }


    /**
     * this function is used to display all the options that admin can do
     *
     * @return
     * @throws ContainerNotFoundException
     * @throws ContainerPoolMaxedOutException
     */


    private void MenuAdmin() throws ContainerNotFoundException, ContainerPoolMaxedOutException, UserNotFoundException, InvalidPercentagemException, FaturaCargaNotFoundException, ContainerFullException, UserTypeNotFoundException, UserExistingException {

        Scanner input = new Scanner(System.in);
        Scanner Contentor = new Scanner(System.in);
        Container tempContainer = null;


        System.out.println("#################################################################");
        System.out.println("######                                                    #######");
        System.out.println("                         WELCOME  ADMIN              ");
        System.out.println("######                      (GESTOR)                      #######");
        System.out.println("######                                                    #######");
        System.out.println("######                                                    #######");
        System.out.println("######   1 - Add Container(Contentor caixa ou embalagem)  #######");
        System.out.println("######           à Container Reusable Pool                #######");
        System.out.println("######  ------------------------------------------------  #######");
        System.out.println("###### 2 - Create a Container with all the possibilities  #######");
        System.out.println("######  ------------------------------------------------  #######");
        System.out.println("######                                                    #######");
        System.out.println("######                                                    #######");
        System.out.println("######                   0 - EXIT                         #######");
        System.out.println("######                                                    #######");
        System.out.println("#################################################################");

        int choose;
        String tipocontainer;
        int sizecontainer;


        choose = input.nextInt();

        while (choose < 10) {
            switch (choose) {

                case 1:
                    System.out.println("##############################################################");
                    System.out.println("Type of container= 'Contentor', 'Caixa' or 'Embalagem':");
                    tipocontainer = Contentor.nextLine();

                    if ("Contentor".equals(tipocontainer)) {
                        sizecontainer = 30;
                    } else if ("Caixa".equals(tipocontainer)) {
                        sizecontainer = 10;
                    } else {
                        sizecontainer = 2;
                    }
                    addcontainer(tipocontainer, sizecontainer);
                    MenuAdmin();
                    break;

                case 2:
                    tempContainer = createcargo();
                    input.nextInt();
                    System.out.println("The invoice will be showed. Press Enter");

                    choose = input.nextInt();
                    faturacao(tempContainer);
                    choose = 11;
                    break;
                case 0:
                    new MenuApp();

                    break;
                default:
                    System.out.println("Invalid character");

            }


            input.nextLine();


        }
    }

    private void MenuMotorista(String Nome) throws ContainerNotFoundException, ContainerPoolMaxedOutException, UserNotFoundException, InvalidPercentagemException, FaturaCargaNotFoundException, ContainerFullException, UserTypeNotFoundException, UserExistingException {


        Scanner input = new Scanner(System.in);
        Scanner Contentor = new Scanner(System.in);
        Container tempContainer = null;
        int choose;


        System.out.println("#################################################################");
        System.out.println("######                                                    #######");
        System.out.println("                         WELCOME  " + Nome + "                    ");
        System.out.println("######                      (GESTOR)                      #######");
        System.out.println("######                                                    #######");
        System.out.println("######                                                    #######");
        System.out.println("######   1 - Add Container(Contentor caixa ou embalagem)  #######");
        System.out.println("######          to Container Reusable Pool                #######");
        System.out.println("######  ------------------------------------------------  #######");
        System.out.println("###### 2 - Create a Container with all the possibilities  #######");
        System.out.println("######  ------------------------------------------------  #######");

        System.out.println("######                   4 - EXIT                         #######");
        System.out.println("######                                                    #######");
        System.out.println("#################################################################");


        String tipocontainer;
        int sizecontainer = 0;
        choose = input.nextInt();

        while (choose < 4) {
            switch (choose) {
                case 1:
                    System.out.println("##############################################################");
                    System.out.println("Type of container= 'Contentor', 'Caixa' ou 'Embalagem':");
                    tipocontainer = Contentor.nextLine();

                    if ("Contetor".equals(tipocontainer)) {
                        sizecontainer = 30;
                    } else if ("Caixa".equals(tipocontainer)) {
                        sizecontainer = 10;
                    } else if ("Embalagem".equals(tipocontainer)){
                        sizecontainer = 5;
                    }
                    addcontainer(tipocontainer, sizecontainer);
                    MenuAdmin();
                    break;

                case 2:
                    tempContainer = createcargo();
                    choose = 3;
                    input.nextInt();
                    break;
                case 3:

                    if (tempContainer != null) {
                        faturacao(tempContainer);
                    } else {
                        System.out.println("No Container Identified.");
                    }
                    break;
                case 4:
                    MenuAdmin();

                default:

                    System.out.println("invalid character");

                    break;

            }


            input.nextLine();

        }
    }


    private void addcontainer(String tipocontainer, int sizecontainer) throws ContainerNotFoundException, ContainerPoolMaxedOutException {

        try {
            ContainerReusablePool.getInstance().addContainer("Container", tipocontainer.trim(), sizecontainer);
            System.out.println("Container of the type " + tipocontainer + " added to the reusable Pool");
        } catch (ContainerPoolMaxedOutException e) {
            System.out.println("Max number of containers reached!!");
        } catch (ContainerNotFoundException e) {
            System.out.println("Type of container doesn't exist!!");
        }
    }


    private Container createcargo() throws ContainerNotFoundException, ContainerPoolMaxedOutException, FaturaCargaNotFoundException, InvalidPercentagemException, UserNotFoundException, ContainerFullException {


        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner contentor = new Scanner(System.in);
        Scanner caixa = new Scanner(System.in);
        Scanner medicamentoSc = new Scanner(System.in);
        int resposta = 0;
        int resposta1 = 0, resposta2;
        int another = 0;


        String nomeContentor = null;
        String nomeCaixa = null;
        String nomeEmbalagem;
        String nomeMedicamento;
        String tipocontainer = null;
        Scanner pausa = new Scanner(System.in);
        String uselessvalue = "";
        int intvalue;

        Container caixacontentor = null;
        Container tempContainer = null;
        Container tempCaixa = null;
        Container tempEmbalagem = null;
        Medicamento medicamento;
        int caixasemconteor = 0;


        System.out.println("Do u want to add a container of the type 'contentor' to your order?");
        System.out.println("1-Yes \n2-No");
        resposta = input.nextInt();

        while (resposta == 1) {

            try {
                tempContainer = ContainerReusablePool.getInstance().getContainer("Contentor");
                System.out.println("What is the name of the 'Contentor'?");

                nomeContentor = contentor.nextLine();
                tempContainer.setName(nomeContentor);
                System.out.println("'Contentor' " + nomeContentor + " created");
                System.out.println("-------------------------------------------------");
                resposta = 0;
               /* try{
                tempContainer.setMotoristaInCharge(UserManager.getInstanceLogin().getAvailableMotorista());
                }catch (UserNotFoundException e){
                    System.out.println("Atention we dont have motoristas to alocate to this trasporte");
                }*/
            } catch (ContainerNotFoundException e) {
                System.out.println("'Contentor' not found, Create one?");
                System.out.println("1-Yes \n2-No");
                resposta = input.nextInt();
                if (resposta == 1) {
                    tipocontainer = "Contentor";
                    try {
                        addcontainer(tipocontainer, 30);
                    } catch (ContainerPoolMaxedOutException e1) {
                        System.out.println("Limit of container reached!! Exiting");
                        resposta = 0;
                    }
                }
            }

        }


//////////////////////////////////////////////////////////CAIXAS///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Do u want to add a container of the type 'caixa' to your order?");
        System.out.println("1-Yes \n2-No");
        resposta = input.nextInt();
        while (resposta == 1) {
            if (resposta == 1) {
                System.out.println("Do u want to add this 'caixa inside the 'contentor' created before?");
                System.out.println("1-Yes \n2-No");
                resposta = input.nextInt();

                if (resposta == 1) {

                    try {
                        tempCaixa = ContainerReusablePool.getInstance().getContainer("Caixa");
                        System.out.println("What is the name of the Caixa?");

                        nomeCaixa = caixa.nextLine();

                        tempCaixa.setName(nomeCaixa);
                        try {
                            tempContainer.addCarga(tempCaixa);

                            System.out.println("'Caixa' " + nomeCaixa + " added to the 'contentor' " + nomeContentor);
                            resposta = 3;
                        } catch (ContainerFullException e) {

                            System.out.println("Contentor Full!!!");
                        } catch (NullPointerException r) {
                            System.out.println("U don't create a 'contentor' before");
                        }


                    } catch (ContainerNotFoundException e) {
                        System.out.println("'Caixa' not found!! Create one?");
                        System.out.println("1-Yes \n2-No");
                        resposta = input.nextInt();
                        if (resposta == 1) {
                            tipocontainer = "Caixa";
                            try {
                                addcontainer(tipocontainer, 10);
                            } catch (ContainerPoolMaxedOutException e1) {
                                System.out.println("Limit of container of type 'caixa' reached!! Exiting");
                                resposta = 10;
                            }
                        }
                    }
                } else {
                    while (resposta == 2) {
                        caixasemconteor = 1;

                        try {
                            tempCaixa = ContainerReusablePool.getInstance().getContainer("Caixa");
                            System.out.println("What is the name of the 'Caixa?");
                            nomeCaixa = caixa.nextLine();

                            tempCaixa.setName(nomeCaixa);
                            System.out.println("'Caixa'  " + nomeCaixa + " created");
                            resposta = 0;
               /* try{
                tempContainer.setMotoristaInCharge(UserManager.getInstanceLogin().getAvailableMotorista());
                }catch (UserNotFoundException e){
                    System.out.println("Atention we dont have motoristas to alocate to this trasporte");
                }*/
                        } catch (ContainerNotFoundException e) {
                            System.out.println("'Caixa' not found, Create one?");
                            System.out.println("1-Yes \n2-No");
                            resposta = input.nextInt();
                            if (resposta == 1) {
                                tipocontainer = "Caixa";
                                try {
                                    addcontainer(tipocontainer, 10);
                                } catch (ContainerPoolMaxedOutException e1) {
                                    System.out.println("Limit of container reached!! Exiting");
                                    resposta = 0;
                                }
                            }
                        }
                    }


                }
            }
        }
        resposta = 1;

        //////////////////////////////////////////////////////////EMBALAGEM///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        while (resposta == 1) {
            System.out.println("Do u want to create a 'Embalagem?'?");
            System.out.println("1-Yes \n2-No");
            resposta = input.nextInt();
            if (resposta == 1) {
                System.out.println("Do u want to add a embalagem to a: 1-Caixa 2-Contentor?");
                resposta = input.nextInt();
                switch (resposta) {

                    case 1:
                        while (resposta == 1) {

                            while (resposta1 == 0) {

                                try {
                                    tempEmbalagem = ContainerReusablePool.getInstance().getContainer("Embalagem");

                                    try {
                                        System.out.println("What is the name of the Embalagem?");
                                        nomeEmbalagem = caixa.nextLine();
                                        tempEmbalagem.setName(nomeEmbalagem);


                                        System.out.println("'Embalagem'  " + nomeEmbalagem + " added to the 'Caixa' " + nomeCaixa);

                                        System.out.println("Add a 'medicamento' to this caixa:");
                                        medicamento = new Medicamento();

                                        System.out.println("Name of medicamento:");
                                        uselessvalue = medicamentoSc.nextLine();
                                        medicamento.setName(uselessvalue);

                                        System.out.println("Size of medicamento:");
                                        intvalue = input.nextInt();
                                        medicamento.setSize(intvalue);

                                        System.out.println("Quantity:");
                                        intvalue = input.nextInt();
                                        medicamento.setQuantity(intvalue);

                                        System.out.println("Unit Value:");
                                        intvalue = input.nextInt();
                                        medicamento.setUnitValue(intvalue);

                                        tempEmbalagem.addCarga(medicamento);
                                        tempCaixa.addCarga(tempEmbalagem);

                                        resposta1 = 1;

                                    } catch (ContainerFullException e) {
                                        System.out.println("'Embalagem' do not contain size enougth!!!");
                                        //   ContainerReusablePool.getInstance().releaseContainerByName("Embalagem",nomeEmbalagem );
                                        resposta1 = 1;
                                    }


                                } catch (ContainerNotFoundException e) {
                                    System.out.println("'Embalagem' not found in reusable pool! Create one?");
                                    System.out.println("1-Yes \n2-No");
                                    resposta = input.nextInt();
                                    if (resposta == 1) {
                                        tipocontainer = "Embalagem";
                                        try {
                                            addcontainer(tipocontainer, 3);
                                            resposta = 1;
                                            resposta1 = 0;
                                        } catch (ContainerPoolMaxedOutException e1) {
                                            System.out.println("Limit of container of type 'caixa' reached!! Exiting");
                                            resposta = 5;
                                        }
                                    }
                                }
                            }

                            System.out.println("Add another medicin??? 1-Yes 2-No");
                            resposta = input.nextInt();
                            if (resposta == 1) {
                                resposta1 = 0;
                                resposta = 1;
                            } else {
                                resposta = 3;
                            }
                        }


                        break;

                    case 2:
                        while (resposta == 2) {
                            while (resposta1 == 0) {

                                try {
                                    tempEmbalagem = ContainerReusablePool.getInstance().getContainer("Embalagem");

                                    try {
                                        System.out.println("What is the name of the Embalagem?");
                                        nomeEmbalagem = caixa.nextLine();
                                        tempEmbalagem.setName(nomeEmbalagem);


                                        System.out.println("'Embalagem' " + nomeEmbalagem + " added to the 'Caixa' " + nomeCaixa);

                                        System.out.println("Add a 'medicamento' to this 'caixa':");
                                        medicamento = new Medicamento();

                                        System.out.println("Name of 'medicamento':");
                                        uselessvalue = medicamentoSc.nextLine();
                                        medicamento.setName(uselessvalue);

                                        System.out.println("Size of 'medicamento:");
                                        intvalue = input.nextInt();
                                        medicamento.setSize(intvalue);

                                        System.out.println("Quantity:");
                                        intvalue = input.nextInt();
                                        medicamento.setQuantity(intvalue);

                                        System.out.println("Unit Value:");
                                        intvalue = input.nextInt();
                                        medicamento.setUnitValue(intvalue);

                                        tempEmbalagem.addCarga(medicamento);
                                        tempContainer.addCarga(tempEmbalagem);
                                        resposta1 = 1;

                                    } catch (ContainerFullException e) {
                                        System.out.println("'Embalagem' Full!!!");
                                        resposta1 = 1;
                                    }


                                } catch (ContainerNotFoundException e) {
                                    System.out.println("'Embalagem' not found in reusable pool! Create one?");
                                    System.out.println("1-Yes \n2-No");
                                    resposta = input.nextInt();
                                    if (resposta == 1) {
                                        tipocontainer = "Embalagem";
                                        try {
                                            addcontainer(tipocontainer, 3);
                                            resposta = 1;
                                            resposta1 = 0;
                                        } catch (ContainerPoolMaxedOutException e1) {
                                            System.out.println("Limit of container of type 'caixa' reached!! Exiting");
                                            resposta = 5;
                                        }
                                    }
                                }
                            }

                            System.out.println("Add another medicin??? 1-Yes 2-No");
                            resposta = input.nextInt();
                            if (resposta == 1) {
                                resposta1 = 0;
                                resposta = 2;
                            } else {
                                resposta = 3;
                            }
                        }


                        break;

                }


            }

        }
        if (caixasemconteor == 1)
            tempContainer = tempCaixa;

        return tempContainer;
    }

    ////////////not working yet
    private void faturacao(Container tempContainer) throws FaturaCargaNotFoundException, InvalidPercentagemException {
        Scanner scan = new Scanner(System.in);
        int escolha;

        linhas(5);
        System.out.println("##############################################################");
        System.out.println("Welcome to the invoice maker");
        System.out.println("##############################################################");
        System.out.println("Faturacao em: 1-Periodo Normal 2- Periodo Especial");
        escolha = scan.nextInt();

        if (escolha == 1) {

            FaturaCarga newFatura = new EmiteFatura(new FaturaCargaPeriodoNormal());
            newFatura.emite(tempContainer);

        } else {
            FaturaCarga newFatura = new EmiteFatura(new FaturaCargaPeriodoNormal());
            newFatura.emite(tempContainer);

        }
    }


    /**
     * This method is used when called to "clear" the screen
     */


    public static int linhas(int numberlines) {

        for (int i = 0; i < numberlines; i++)
            System.out.println("\n");

        return 1;
    }
}







/*Scanner input = new Scanner(System.in);
        Scanner contentornome = new Scanner(System.in);
        String contentornomeS=null;
        String caixanomeS=null;
        String embalagemnomeS=null;
        String medicamentornomeS=null;
        Container tempcontainer = null;
        Container tempcaixa = null;
        Container tempembalagem = null;
        Container tempmedicamento = null;
        int resposta;
        int temp;

        //insert a contentor if pretended
        System.out.println("Create a container with all u can put inside him");
        System.out.println("##############################################################");
        System.out.println("Do u want to create a 'contentor'?  1-Yes  2-No");
        resposta = input.nextInt();


        if(resposta==1){
            System.out.println("##############################################################");
            System.out.println("what is the name of the 'contentor'?");
            contentornomeS = input.nextLine();

            try{
                tempcontainer = ContainerReusablePool.getInstance().getContainer("Contentor");
                tempcontainer.setName(contentornomeS);

            } catch (ContainerNotFoundException e)
            {
                System.out.println("Container of type 'Contentor' not found: Do u wish create one? 1-Yes 2-No");
                resposta = input.nextInt();
                if(resposta==1){
                    String a = "Contentor";
                   try {
                       addcontainer(a, 30);
                       System.out.println("ollllllllllllllllla");
                       tempcontainer = ContainerReusablePool.getInstance().getContainer("Contentor");
                       tempcontainer.setName(contentornomeS);
                   } catch (ContainerPoolMaxedOutException e1) {
                       System.out.println("Max number of 'Contentor' reached!!");

                }
            }
        }}else{
            contentornomeS=null;
        }


        System.out.println("##############################################################");
        System.out.println("Do u want to create a 'caixa' inside a contentor previous created?  1-Yes  2-No");
        resposta= input.nextInt();
        if(resposta==1){
        while(resposta==1) {
            System.out.println("##############################################################");
            System.out.println("what is the name of the 'caixa'?");
            caixanomeS = input.nextLine();
            tempcaixa = ContainerReusablePool.getInstance().getContainer("Caixa");
            tempcaixa.setName(caixanomeS);//////////////////AVISO AQUI PARA MUDAR

            try {
               tempcontainer.addCarga(tempcaixa);
           } catch (ContainerFullException e) {
               System.out.println("Contentor is full");
           }
            System.out.println("##############################################################");
            System.out.println("Add another 'caixa' to "+ contentornome +"?");
            System.out.println("1-Yes  2-No");
            resposta=input.nextInt();
                }
        }
        else {
            caixanomeS=null;
        }





        System.out.println("##############################################################");
        System.out.println("Do u want to create a 'embalagem' inside a 'caixa' previous created?  1-Yes  2-No");
        resposta= input.nextInt();
        if(resposta==1){
            while(resposta==1) {
                System.out.println("##############################################################");
                System.out.println("what is the name of the 'embalagem'?");
                embalagemnomeS = input.nextLine();
                tempembalagem = ContainerReusablePool.getInstance().getContainer("Embalagem");
                tempcaixa.setName(embalagemnomeS);
                try {
                    tempcaixa.addCarga(tempembalagem);
                } catch (ContainerFullException e) {
                System.out.println("Contentor is full");
            }
            System.out.println("##############################################################");
            System.out.println("Add another 'caixa' to "+ contentornome +"?");
            System.out.println("1-Yes  2-No");
            resposta=input.nextInt();
        }
                System.out.println("##############################################################");
                System.out.println("Add another 'caixa' to "+ caixanomeS +"?");
                System.out.println("1-Yes  2-No");
                resposta=input.nextInt();
            }

        else {
            caixanomeS=null;
        }


        System.out.println("##############################################################");
        System.out.println("Do u want to create a 'Medicamento' inside a 'Embalagem' previous created?  1-Yes  2-No");
        resposta= input.nextInt();
        if(resposta==1){
            while(resposta==1) {
                System.out.println("##############################################################");
                System.out.println("what is the name of the 'Medicamento'?");
                medicamentornomeS = input.nextLine();
                Medicamento meditemp= new Medicamento();
                meditemp.setName(medicamentornomeS);

                System.out.println("Size of Medicamento:");
                temp = input.nextInt();
                meditemp.setSize(temp);

                System.out.println("Quantity of Medicamento:");
                temp = input.nextInt();
                meditemp.setQuantity(temp);

                System.out.println("Unit value of Medicamento:");
                temp = input.nextInt();
                meditemp.setUnitValue(temp);




                try {
                    tempembalagem.addCarga(tempmedicamento);
                }  catch (ContainerFullException e) {
                System.out.println("Contentor is full");
            }
            System.out.println("##############################################################");
            System.out.println("Add another 'caixa' to "+ contentornome +"?");
            System.out.println("1-Yes  2-No");
            resposta=input.nextInt();
        }
                System.out.println("##############################################################");
                System.out.println("Add another 'medicamento' to "+ embalagemnomeS +"?");
                System.out.println("1-Yes  2-No");
                resposta=input.nextInt();
            }

        else {
            caixanomeS=null;
        }


        FaturaCarga faturaCarga = new FaturaCarga();
        int idFatura = faturaCarga.addFatura(new FaturaCargaPeriodoNormal());
        faturaCarga.setPercentagem(idFatura, (float)50);
        faturaCarga.setFatura(idFatura, tempcontainer);
        //Release Motorista
       // UserManager.getInstanceLogin().freeMotorista(tempcontainer.getMotoristaInCharge().getUsername());
        //Release Containers
        ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "Cargo-1");
        ContainerReusablePool.getInstance().releaseContainerByName("Caixa", "Caixa-1");
        ContainerReusablePool.getInstance().releaseContainerByName("Embalagem", "Embalagem-1");
        ContainerReusablePool.getInstance().releaseContainerByName("Embalagem", "Embalagem-2");

        //Percentagem
        System.out.println(faturaCarga.getPercentagem(idFatura));
        //Descritivo
        System.out.println(faturaCarga.getDescritivoFatura(idFatura));





*/



































 /*
        Frame f= new Frame("Menu and MenuItem Example");
        MenuBar mb=new MenuBar();
        Menu menu=new Menu("Menu");
        Menu submenu=new Menu("Sub Menu");
        MenuItem i1=new MenuItem("Item 1");
        MenuItem i2=new MenuItem("Item 2");
        MenuItem i3=new MenuItem("Item 3");
        MenuItem i4=new MenuItem("Item 4");
        MenuItem i5=new MenuItem("Item 5");
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        submenu.add(i4);
        submenu.add(i5);
        menu.add(submenu);
        mb.add(menu);
        f.setMenuBar(mb);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
        */

