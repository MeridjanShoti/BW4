package it.epicode.entities.titoli_di_viaggio;


public class ConteggioByPuntoVenditaEData {
    private Long count;
    private Long luogoEmissione;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getLuogoEmissione() {
        return luogoEmissione;
    }

    public void setLuogoEmissione(Long LuogoEmissione) {
        this.luogoEmissione = LuogoEmissione;
    }

    public ConteggioByPuntoVenditaEData( Long luogoEmissione, Long count) {
        this.count = count;
        this.luogoEmissione = luogoEmissione;
    }

    public ConteggioByPuntoVenditaEData() {
    }

    @Override
    public String toString() {
        return "LuogoEmissione = " + luogoEmissione +", count = " + count;
    }


}