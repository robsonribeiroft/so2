import java.util.ArrayList;

public class Otimo extends Thread{
    private int minFrame = 0;
    private int maxFrame = 0;
    //private ArrayList <Integer> listaPaginas = new ArrayList<Integer>();
    private ArrayList<Integer> listaMemoria;
    private ArrayList <Integer>listaProxRef ;

    private ArrayList <Integer> listaResultadoAmostras = new ArrayList<Integer>();

    public Otimo(int minFrame, int maxFrame)
    {
        this.minFrame = minFrame;
        this.maxFrame = maxFrame;
        new Thread(this).start();

    }

    @Override
    public void run() {
        super.run();
        executar();
    }

    public void executar()
    {
        //Faz uma simulação para cada valor de amostra
        while (this.minFrame <= this.maxFrame)
        {
            listaMemoria = new ArrayList<>();
            listaProxRef = new ArrayList<>();
            int numTotalAcerto = 0, varOne = 0;
            //Esse While vai percorrer toda a lista de páginas e fazer as operações necessárias
            while(varOne < Singleton.getInstance().data.values.size())
            {
                //Verifica se a página já se encontra na memória
                if(listaMemoria.contains(Singleton.getInstance().data.values.get(varOne)))
                    numTotalAcerto++;
                else
                {
                    //Verificando se a memória está toda ocupada
                    if (listaMemoria.size() < minFrame)
                    {
                        listaMemoria.add(Singleton.getInstance().data.values.get(varOne));
                        listaProxRef.add(0);
                    }
                    //Ocorreu falta de página
                    else
                    {
                        int varTwo = 0;
                        while (varTwo < listaMemoria.size())
                        {
                            int numRef = 0;
                            listaProxRef.set(varTwo, numRef);
                            int aux = varOne;
                            boolean bool = false;
                            while(aux < Singleton.getInstance().data.values.size() && !bool)
                            {
                                numRef++;
                                listaProxRef.set(varTwo,numRef);
                                if (listaMemoria.get(varTwo) == Singleton.getInstance().data.values.get(aux))
                                    bool = true;
                                aux++;
                            }

                            varTwo++;
                        }
                        int varThree = 0;
                        boolean boolOne = false;
                        while(varThree < listaMemoria.size() && !boolOne)
                        {
                            int max = 0;

                            for (int varFour = 0; varFour < listaProxRef.size(); varFour++)
                            {
                                if(listaProxRef.get(varFour)>max)
                                    max = listaProxRef.get(varFour);
                            }

                            if(max == listaProxRef.get(varThree))
                            {
                                listaMemoria.remove(varThree);
                                listaProxRef.remove(varThree);

                                listaMemoria.add(Singleton.getInstance().data.values.get(varOne));
                                listaProxRef.add(0);
                                boolOne = true;
                            }
                            varThree++;
                        }
                    }
                }
                varOne++;
            }
            System.out.println("OTIMO - "+ minFrame+"->"+ + numTotalAcerto);
            Singleton.getInstance().resultOtimo.add(numTotalAcerto);
            this.minFrame++;
        }

    }
}