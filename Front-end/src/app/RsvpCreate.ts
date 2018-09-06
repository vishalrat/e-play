import{RsvpInvitation} from './RsvpInvitation';
export class RsvpCreate{
    id:number;
    name:string;
    userName:string;
    startDate:Date;
    startTime:String;
    endDate:Date;
    endTime:String;
    city:string;
    location:string;
    poster:string;
    type:string;
    description:string;
    performers:string;
    termsAndCondition:string;
    notes:string;
    numberOfAttendees:number;
    isCompleted:boolean;
    rsvpInvitationList: RsvpInvitation[];
    
    


}
