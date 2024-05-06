import React from 'react';
import './Notification.css'; // Import your CSS file for styling

interface NotificationProps {
    message: string;
    onClose: () => void;
}

const Notification: React.FC<NotificationProps> = ({message, onClose}) => {
    return (
        <div className="notification-container">
            <div className="notification-content">
                <p>{message}</p>
                <button className="close-button" onClick={onClose}>X</button>
            </div>
        </div>
    );
};

export default Notification;
