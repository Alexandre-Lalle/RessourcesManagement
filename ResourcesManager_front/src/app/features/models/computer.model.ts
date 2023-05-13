import { Resource } from "./resource.model";

export interface Computer extends Resource {
    disk: string;
    ram: number;
    cpu: string;
    screen: string;
    selected? : boolean;

    // public constructor(name: string, dateOfRequest: Date, state: number, disk: string, ram: number, cpu: string, screen: string) {
    //     super(name, dateOfRequest, state);
    //     this.disk = disk;
    //     this.ram = ram;
    //     this.cpu = cpu;
    //     this.screen = screen;
    // }

    // public override getResourceType(): string {
    //     return "Ordinateur";
    // }
}