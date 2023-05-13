import { Report } from "./report.model";
import { Resource } from "./resource.model";
import { Teacher } from "./teacher.mode";

export interface Failure {
    id: number;
    date: Date;
    resourceBrand: string;
    resourceType: string;
    teacherName: string;
    teacherEmail: string;
    processed: boolean;
}