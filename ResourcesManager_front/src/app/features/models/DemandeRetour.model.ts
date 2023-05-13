import { Printer } from "./printer.model";
import { Computer } from "./computer.model";
export interface DemandeRetour  {
    id?:number; 
    message: string;
    imprimanteDto?:Printer;
    ordinateurDto?:Computer;
   
}