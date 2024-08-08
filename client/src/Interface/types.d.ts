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

export interface Notification {
    notificationId: number;
    userId: number;
    type: string;
    message: string;
    read: boolean;
    createdAt: string;
  }