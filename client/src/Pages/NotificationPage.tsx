import { useEffect, useState } from "react";
import config from "../config";
import { Notification } from '../Interface/types';
import NotificationDetail from '../Components/NotificationDetail';
import { useAuth } from "../Context/AuthContext";
import '../CSS/notification.css';
import 'bootstrap/dist/css/bootstrap.min.css';

const NotificationsPage: React.FC = () => {
  const [notifications, setNotifications] = useState<Notification[]>([]);
  const [selectedNotificationId, setSelectedNotificationId] = useState<number | null>(null);
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
        console.log('Error fetching notifications.');
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

  const handleNotificationClick = (notification: Notification) => {
    if (selectedNotificationId === notification.notificationId) {
      setSelectedNotificationId(null); // Close if the same notification is clicked
    } else {
      setSelectedNotificationId(notification.notificationId); // Open the clicked notification
      if (!notification.read) {
        markAsRead(notification.notificationId);
      }
    }
  };

  const formatDate = (dateString: string) => {
    const options: Intl.DateTimeFormatOptions = {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
    };
    return new Date(dateString).toLocaleString(undefined, options);
  };

  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100 bg-dark text-light">
      <div className="card shadow-lg p-4 w-100 " style={{ maxWidth: '800px', backgroundColor: 'rgba(0, 0, 0, 0.85)' }}>
        <h2 id="notification-h2" className="mb-4">Notifications</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        <ul className="list-group list-group-flush notif-ul">
          {notifications.map((notification) => (
            <li
              key={notification.notificationId}
              className={`list-group-item d-flex justify-content-between align-items-center ${
                notification.read ? 'bg-dark text-light' : 'bg-secondary text-light'
              }`}
              onClick={() => handleNotificationClick(notification)}
              style={{
                cursor: 'pointer',
                textDecoration: notification.read ? 'none' : 'underline',
                fontWeight: notification.read ? 'normal' : 'bold',
              }}
            >
              <div>
                <span>{notification.message}</span>
                <br />
                <small>{formatDate(notification.createdAt)}</small> {/* Display formatted date */}
                {!notification.read && (
                  <span className="badge bg-danger rounded-pill">New</span>
                )}
              </div>
              
              {selectedNotificationId === notification.notificationId && (
                <NotificationDetail
                  notification={notification}
                  onClose={() => setSelectedNotificationId(null)}
                />
              )}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default NotificationsPage;
