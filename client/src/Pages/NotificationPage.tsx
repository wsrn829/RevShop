import { useEffect, useState } from "react";
import config from "../config";
import { Notification } from '../Interface/types';
import NotificationDetail from '../Components/NotificationDetail'
import { useAuth } from "../Context/AuthContext";

const NotificationsPage: React.FC = () => {
    const [notifications, setNotifications] = useState<Notification[]>([]);
    const [selectedNotification, setSelectedNotification] = useState<Notification | null>(null);
    const [error, setError] = useState('');
  
    const { user } = useAuth();
  
    useEffect(() => {
      const fetchNotifications = async () => {
        try {
          if (!user) {
            throw new Error('User not authenticated');
          }
  
          const response = await fetch(`${config.BASE_URL}/api/notifications/user/${user.userId}`, {
            credentials: 'include',
          });
  
          if (!response.ok) {
            throw new Error('Failed to fetch notifications');
          }
  
          const data = await response.json();
          setNotifications(data);
        } catch (error) {
          setError('Error fetching notifications.');
        }
      };
  
      fetchNotifications();
    }, [user]);
  

    const markAsRead = async (notificationId: number) => {
      try {
        const response = await fetch(
          `${config.BASE_URL}/api/notifications/${notificationId}?read=true`,
          {
            method: 'PUT',
            credentials: 'include',
          }
        );
  
        if (!response.ok) {
          throw new Error('Failed to update notification status');
        }
  
   
        setNotifications((prev) =>
          prev.map((n) =>
            n.notificationId === notificationId ? { ...n, read: true } : n
          )
        );
      } catch (error) {
        setError('Error updating notification status.');
      }
    };
  
    return (
      <div>
        <h2>Notifications</h2>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <ul>
          {notifications.length > 0 ? notifications.map((notification) => (
            <li
              key={notification.notificationId}
              onClick={() => {
                setSelectedNotification(notification);
                if (!notification.read) {
                  markAsRead(notification.notificationId);
                }
              }}
              style={{
                cursor: 'pointer',
                textDecoration: notification.read ? 'none' : 'underline',
                fontWeight: notification.read ? 'normal' : 'bold',
              }}
            >
              {notification.message}
            </li>
          )): <p>No notifications yet.</p>}
        </ul>
  
        {selectedNotification && (
          <NotificationDetail
            notification={selectedNotification}
            onClose={() => setSelectedNotification(null)}
          />
        )}
      </div>
    );
  };
  
  export default NotificationsPage;