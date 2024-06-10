package main;

import entity.Entity;

public class CollisionManager {

    public GamePanel gamePanel;

    public CollisionManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    } // CollisionManager(.)

    public void checkTile(final Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.hitBox.x;
        int entityRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityTopWorldY = entity.worldY + entity.hitBox.y;
        int entityBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
        int entityRightCol = entityRightWorldX/gamePanel.tileSize;
        int entityTopRow = entityTopWorldY/gamePanel.tileSize;
        int entityBotRow = entityBottomWorldY/gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTimeNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTimeNum[entityRightCol][entityTopRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } // if
                break;
            case "down":
                entityBotRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTimeNum[entityLeftCol][entityBotRow];
                tileNum2 = gamePanel.tileManager.mapTimeNum[entityRightCol][entityBotRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } // if
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTimeNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTimeNum[entityRightCol][entityBotRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } // if
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTimeNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTimeNum[entityLeftCol][entityBotRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } // if
                break;
        } // switch

    } // checkTile(.)

} // CollisionManager
