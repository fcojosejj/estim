export class VideojuegosDEOFetch {
    constructor(apiurl){
        this.srvUrl = apiurl;
        this.respuestaValida = false;
    }

    getTodos(){
        return fetch(this.srvUrl)
            .then(response => this.compruebaRespuesta(response))
            .then(response => this.devolverRespuesta(response))
    }

    getById(id = 0){
        return fetch(this.srvUrl + "/" + id)
            .then(response => this.compruebaRespuesta(response))
            .then(response => this.devolverRespuesta(response))
    }

    crea(juego){
        return fetch(this.srvUrl, {
            method: 'POST',
            body: JSON.stringify(juego),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        })
            .then(response => this.compruebaRespuesta(response))
            .then(response => this.devolverRespuesta(response))
    }

    guarda(juego){
        return fetch(`${this.srvUrl}/${juego.id}`, {
            method: 'PUT',
            body: JSON.stringify(juego),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        })
            .then(response => this.compruebaRespuesta(response))
            .then(response => this.devolverRespuesta(response))
    }

    borra(id = 0){
        return fetch(this.srvUrl + "/" + id, {
            method: 'DELETE'
        })
            .then(response => this.compruebaRespuesta(response))
            .then(response => this.devolverRespuesta(response))
    }

    compruebaRespuesta(response) {
        this.respuestaValida=response.ok;
        return response.json();
    }
    devolverRespuesta (json) {
        if (!this.respuestaValida) {
            return Promise.reject(json);
        }
        return json;
    }
}