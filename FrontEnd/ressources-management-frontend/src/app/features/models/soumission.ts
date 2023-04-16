import { Offre } from "./offre";

export interface Soumission {
    id?:number; 
    marque:string; 
    prix:number;
    etat?:number;   
    offreDto:Offre; 
}
