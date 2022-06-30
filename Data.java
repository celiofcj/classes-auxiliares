public class Data {
    private int diaDoMes = 0;
    private int mes = 0;
    private int ano = 0;
    private int hora = 0;
    private int minuto = 0;
    private String formatacaoPadrao = "mm:hh DD/MM/AAAA";
    private boolean horaMin;

    public Data(int diaDoMes, int mes, int ano){
        this.diaDoMes = diaDoMes;
        this.mes = mes;
        this.ano = ano;
    }

    public Data(int diaDoMes, int mes, int ano, int minuto, int hora){
        this.diaDoMes = diaDoMes;
        this.mes = mes;
        this.ano = ano;
        this.minuto = minuto;
        this.hora = hora;
    }

    public Data(String dataHora, String formatacao){
        if(dataHora.length() != formatacao.length()){
            return;
        }

        int i = 0;
        while(i < formatacao.length()){
            switch (formatacao.charAt(i)){
                case 'm':
                    this.minuto = Integer.parseInt(dataHora.substring(i, i+1));
                    if(i + 1 != formatacao.length() -1)
                        i += 2;

                case 'h':
                    this.hora = Integer.parseInt(dataHora.substring(i, i+1));
                    if(i + 1 != formatacao.length() -1)
                        i += 2;

                case 'D':
                    this.diaDoMes = Integer.parseInt(dataHora.substring(i, i+1));
                    if(i + 1 != formatacao.length() -1)
                        i += 2;

                case 'M':
                    this.mes = Integer.parseInt(dataHora.substring(i, i+1));
                    if(i + 1 != formatacao.length() -1)
                        i += 2;

                case 'A':
                    this.mes = Integer.parseInt(dataHora.substring(i, i+3));
                    if(i + 3 != formatacao.length() -1)
                        i += 4;
                    break;

                default:
                    if(i != formatacao.length() -1)
                        i++;

            }

        }
    }

    public int getDiaDoMes() {
        return diaDoMes;
    }

    public void setDiaDoMes(int diaDoMes) {
        this.diaDoMes = diaDoMes;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public String setFormato(String formatacao){
        if(formatacao.matches("")){
            throw new RuntimeException();
        }

        if(!formatacao.contains("DD") || !formatacao.contains("MM") || !formatacao.contains("AAAA")){
            throw new RuntimeException();
        }

        if((!formatacao.contains("hh") && formatacao.contains("mm")) ||
                (formatacao.contains("mm") && !formatacao.contains("mm"))){
            throw new RuntimeException();
        }

        if(!formatacao.contains("mm") && !formatacao.contains("mm")){
            horaMin = false;
        }

        return formatacao;
    }

    public void setFormatoPadrao(String formatacao){
        this.formatacaoPadrao = setFormato(formatacao);
    }

    public String getData(){
        return getData(formatacaoPadrao);
    }

    public String getData(String formatacao){
        String dataString = "";
        if(horaMin){
            int i = 0;
            while(i < formatacao.length()){
                switch (formatacao.charAt(i)) {
                    case 'm':
                        Integer minutoInt = minuto;
                        dataString += minutoInt.toString();
                        if (i + 1 != formatacao.length() - 1)
                            i += 2;
                        break;

                    case 'h':
                        Integer horaInt = hora;
                        dataString += horaInt.toString();
                        if (i + 1 != formatacao.length() - 1)
                            i += 2;
                        break;

                    case 'D':
                        Integer diaInt = diaDoMes;
                        dataString += diaInt.toString();
                        if (i + 1 != formatacao.length() - 1)
                            i += 2;
                        break;

                    case 'M':
                        Integer mesInt = mes;
                        dataString += mesInt.toString();
                        if (i + 1 != formatacao.length() - 1)
                            i += 2;
                        break;

                    case 'A':
                        Integer anoInt = ano;
                        dataString += anoInt.toString();
                        if (i + 3 != formatacao.length() - 1)
                            i += 4;
                        break;

                    default:
                        dataString += formatacao.charAt(i);
                        if (i != formatacao.length() - 1)
                            i++;
                }
            }
        }
        else{
            int i = 0;
            while(i < formatacao.length()) {
                while (i < formatacao.length()) {
                    switch (formatacao.charAt(i)) {
                        case 'D':
                            Integer diaInt = diaDoMes;
                            if(diaDoMes < 10)
                                dataString += "0";
                            dataString += diaInt.toString();
                                i += 2;
                            break;

                        case 'M':
                            Integer mesInt = mes;
                            if(mes < 10)
                                dataString += "0";
                            dataString += mesInt.toString();
                            if (i + 1 != formatacao.length() - 1)
                                i += 2;
                            break;

                        case 'A':
                            Integer anoInt = ano;
                            dataString += anoInt.toString();
                                i += 4;
                            break;

                        default:
                            dataString += formatacao.charAt(i);
                            if (i != formatacao.length() - 1)
                                i++;
                    }
                }
            }
        }
        return dataString;
    }


}
