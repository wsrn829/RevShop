export interface User {
    userId: number;
    username: string;
    email: string;
    firstName?: string;
    lastName?: string;
    type: string;
    businessDetails?: string;
    banned: boolean;

}