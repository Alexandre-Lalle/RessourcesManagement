import { Computer } from "./computer.model";
import { Printer } from "./printer.model";

export interface Besoin{
    imprimanteDto?:Printer,
    ordinateurDto?:Computer
}