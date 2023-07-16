package model;

import java.util.List;

public class Relatorio {
        private Rebelde rebelde;
        private int quantidadeRelatorios;


        public Relatorio(Rebelde rebelde) {

            this.rebelde = rebelde;
            this.quantidadeRelatorios = 0;
        }

        public Relatorio () {

        }


        public Rebelde getRebelde() {

            return rebelde;
        }

        public int getQuantidadeRelatorios() {
            return quantidadeRelatorios;
        }

        public void incrementarQuantidadeRelatorios() {
            quantidadeRelatorios++;
            if (quantidadeRelatorios >= 3) {
                rebelde.setStatus(false);
            }
        }

    public void setRebelde(Rebelde rebelde) {
        this.rebelde = rebelde;
    }

    public void setQuantidadeRelatorios(int quantidadeRelatorios) {
        this.quantidadeRelatorios = quantidadeRelatorios;
    }
}
