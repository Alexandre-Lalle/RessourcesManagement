export interface Report{
    id?:number;
    date?:string;
    explication?:string;
    frequency?:string;
    order?:string;
    seen?:boolean;
    failure:{id:number,date:Date, processed:boolean};
}