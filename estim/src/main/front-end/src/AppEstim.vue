<template>
    <EstimHeader/>
      <VideojuegosList v-if="videojuego.id === undefined"
                    :datos="videojuegos"
                    @select-videojuego="getVideojuego"
                    @delete-videojuego="borraVideojuego"/>
      <VideojuegosForm v-if="videojuego.id !== undefined"
                    :videojuego="videojuego"
                    @update-videojuego="videojuegoActualizado"/>
  <button class="btn btn-primary" v-if="videojuego.id === undefined" @click.prevent="altaVideojuego()">Nuevo videojuego</button>
  <EstimFooter/>
</template>

<script>
import EstimHeader from "@/components/commons/EstimHeader.vue";
import EstimFooter from "@/components/commons/EstimFooter.vue";
import VideojuegosList from "@/components/VideojuegosList.vue";
import VideojuegosForm from "@/components/VideojuegosForm.vue";
import {VideojuegosDEOFetch} from "@/services/videojuegos.services";

const videojuegosDAO = new VideojuegosDEOFetch('http://localhost:8080/estim-1.0-SNAPSHOT/api/videojuegos')
export default {
    components: {EstimHeader, EstimFooter, VideojuegosList, VideojuegosForm},
    created(){
      this.srvUrl = "http://localhost:8080/estim-1.0-SNAPSHOT/api/videojuegos"
      this.cargaVideojuegos()
    },
    provide: {
      videojuegosDAO
    },
    data() {
        return {
            videojuegos: [],
            videojuego: {},
            errores: {}
        }
    },
    methods: {
        async cargaVideojuegos() {
            try{
                this.videojuegos = await videojuegosDAO.getTodos()
                this.videojuego = {}
            } catch (ex) {
                console.error('Error en la conexión');
            }
        },
        async getVideojuego(videojuegoId){
            try{
                this.videojuego = await videojuegosDAO.getById(videojuegoId)
            } catch (ex) {
                console.error('Error en la conexión')
            }
        },
        async borraVideojuego(videojuegoId){
            if(videojuegoId > 0){
                try{
                    await videojuegosDAO.borra(videojuegoId)
                    this.cargaVideojuegos()
                } catch (ex) {
                    console.error('Error en la conexión')
                }
            }
        },
      altaVideojuego() {
        this.errorMsgs={ }
        this.videojuego = {id: 0, nombre: '', descripcion: '', desarrolladora: '', precio: 0, valoracion: 0}
      },
      async videojuegoActualizado() {
        console.log("actualizado videojuego en servidor")
        this.cargaVideojuegos()
      },
    }
}
</script>