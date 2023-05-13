import { Resource } from "./resource.model";

export interface Printer extends Resource{
    printSpeed:number;
    resolution:string;
    selected? : boolean;

    // public constructor(name:string, dateOfRequest:Date,state:number, printSpeed:number, resolution:string){
    //     super(name,dateOfRequest,state);
    //     this.printSpeed = printSpeed;
    //     this.resolution = resolution;
    // }

    // public override getResourceType():string{
    //     return "Imprimente";
    // }
}