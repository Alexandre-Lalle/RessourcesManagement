import { User } from "./user.model";

export interface Teacher extends User{
    name?: any;
    department:string;

    // constructor(id:number, firstNAme:string, lastName:string, email:string, department:string){
    //     super(id,firstNAme,lastName,email,"teacher");
    //     this.department = department;
    // }
}