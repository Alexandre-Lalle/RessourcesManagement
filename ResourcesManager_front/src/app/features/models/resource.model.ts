import { Panne } from "./panne";
import { Teacher } from "./teacher.mode";

export interface Resource {
    id: number;
    barCode: any;
    name: string;
    brand: string;
    dateOfRequest: Date;
    deliveryDate: Date;
    warrantyDate: Date;
    assignmentDate: Date;
    state: number;
    qty?: 0,
    providerName?: String;
    failureDtoList?: Panne[]
    resourceType: string;
    teachers?: Teacher[];

    // constructor(name:string, dateOfRequest:Date, state:number){
    //     this.name = name;
    //     this.dateOfRequest = dateOfRequest;
    //     this.state = state;
    //     this.deliveryDate=new Date();
    //     this.warrantyDate=new Date();
    //     this.assignmentDate=new Date();
    //     this.resourceType='';
    // }

    // public getResourceType():string{
    //     return "Resource";
    // };
}


export interface ResourcePage {
    demands: Array<Resource>;
    page: number;
    size: number;
    totalPAges: number;
}