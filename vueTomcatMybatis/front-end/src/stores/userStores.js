import {defineStore} from "pinia";

const userStores=defineStore('user',{
    state:()=>({
        user:null
    }),
    actions:{
        setUser(user){
            this.user=user
        },
        clearUser(){
            this.user=null
        },
        getUser(){
            return this.user
        }
    }
})
export default userStores