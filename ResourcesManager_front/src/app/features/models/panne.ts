import { Resource } from "./resource.model";
import { Teacher } from "./teacher.mode";

export interface Panne {
    dateApp?:Date; 
    traiter?:boolean; 
    explication?:string; 
    ressourceDto?:Resource; 
}
