import { User } from "./user.model";

export interface Provider extends User{
    name:string;
    manager:string;
}