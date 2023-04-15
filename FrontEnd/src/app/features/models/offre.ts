import { Computer } from "./computer.model";
import { Printer } from "./printer.model";

export interface Offre {
    id:number; 
    dateDebut?:Date; 
    dateFin?:Date; 
    ordinateurDtoList?:Computer[]; 
    imprimanteDtoList?:Printer[]; 
}
