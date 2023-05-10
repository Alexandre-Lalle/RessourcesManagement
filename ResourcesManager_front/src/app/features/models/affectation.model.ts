import { Resource } from "./resource.model";
import { Teacher } from "./teacher.mode";

export interface Affectation {

    id?: number;
    dateAffectation: Date;
    teacherList: Teacher[];
    resourceId: number;
}
