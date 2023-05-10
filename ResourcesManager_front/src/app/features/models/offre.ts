import { Computer } from "./computer.model";
import { Printer } from "./printer.model";
import { Soumission } from "./soumission";

export interface Offre {
    id:number; 
    dateDebut?:Date; 
    dateFin?:Date; 
    ordinateurDtoList:Computer[]; 
    imprimanteDtoList:Printer[]; 
    soumissionDTOList?: Soumission[];
}
