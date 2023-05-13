import { Resource } from "./resource.model";
import { Teacher } from "./teacher.mode";

export interface Panne {
    date?:Date;
    state?:boolean;
    ressourceDto?:Resource; 
}
