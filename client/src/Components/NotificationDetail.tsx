import { Notification } from "../Interface/types";


interface NotificationDetailProps {
  notification: Notification;
  onClose: () => void;
}

const NotificationDetail: React.FC<NotificationDetailProps> = ({ notification, onClose }) => {
  return (
    <div>
      <h3>Notification Details</h3>
      <p>Type: {notification.type}</p>
      <p>Message: {notification.message}</p>
      <p>Date: {notification.createdAt}</p>
      <button onClick={onClose}>Close</button>
    </div>
  );
};

export default NotificationDetail;