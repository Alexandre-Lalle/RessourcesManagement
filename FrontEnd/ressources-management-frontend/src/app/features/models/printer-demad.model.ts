import { Printer } from "./printer.model";
import { User } from "./user.model";

export interface PrinterDemand {
    teachers: User[];
    printer: Printer;

    // constructor(teachers: User[], printer: Printer) {
    //     this.teachers = teachers;
    //     this.printer = printer;
    // }

    // get(str: string): string {
    //     return str;
    // }
}