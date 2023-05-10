export interface Report {
    id: number;
    date: Date;
    explication: string;
    frequency: string;
    order:string;
    resourceType: string;
    teacherType: string;
    teacherName: string;
    teacherEmail: string;
    teacherDep: string;
    fournisseurName:string;
    fournisseurManager:string;
    fournisseurId:number;
    resourceId:number;
  }