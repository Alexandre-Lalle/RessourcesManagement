import { Computer } from "./computer.model";
import { Printer } from "./printer.model";
import { Teacher } from "./teacher.mode";

export interface DemandForm{
    printer:Printer;
    computer:Computer;
    teachers:Teacher;

    // public constructor(printer:Printer, computer:Computer, teachers:Teacher){
    //     this.computer = computer;
    //     this.printer = printer;
    //     this.teachers = teachers;
    // }
}