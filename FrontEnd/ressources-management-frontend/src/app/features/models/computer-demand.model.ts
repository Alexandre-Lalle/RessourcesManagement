import { Computer } from "./computer.model";
import { User } from "./user.model";

export interface ComputerDemand {
    computer: Computer;
    teachers: User[];

    // constructor(teachers: User[], computer: Computer) {
    //     this.teachers = teachers;
    //     this.computer = computer;
    // }

    // get(str: string): string {
    //     return str;
    // }
}