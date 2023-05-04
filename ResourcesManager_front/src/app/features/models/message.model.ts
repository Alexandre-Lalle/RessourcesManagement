import { Director } from "./director.model";

export interface Message {
    id: number;
    subject:string;
    content: string;
    date:Date;
    departmentDirector:Director;

    // constructor(id: number, subject:string, content: string, date:Date, departmentDirector:Director) {
    //     this.id = id;
    //     this.subject = subject;
    //     this.content = content;
    //     this.date = date;
    //     this.departmentDirector = departmentDirector;
    // }
}