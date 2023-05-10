import { Resource } from "./resource.model";
import { Teacher } from "./teacher.mode";
import { User } from "./user.model";

export interface Demand{
    resource:Resource;
    teachers:Teacher[];

    // constructor(teachers:User[], resource:Resource){
    //     this.teachers = teachers;
    //     this.resource = resource;
    // }

    // get(str:string):string{
    //     return str;
    // }
}

export interface DemandPage{
    demands:Array<Demand>;
    page:number;
    size:number;
    totalPAges:number;
}