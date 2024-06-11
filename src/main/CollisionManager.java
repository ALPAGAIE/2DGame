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

    public int checkObject(final Entity e, final boolean player) {

        int index = 11;

        for(int i = 0; i < gamePanel.obj.length; i++) {
            if(gamePanel.obj[i] != null) {
                e.hitBox.x += e.worldX;
                e.hitBox.y += e.worldY;

                gamePanel.obj[i].hitBox.x += gamePanel.obj[i].worldX;
                gamePanel.obj[i].hitBox.y += gamePanel.obj[i].worldY;

                switch (e.direction) {
                    case "up":
                        e.hitBox.y -= e.speed;
                        if(e.hitBox.intersects(gamePanel.obj[i].hitBox)) {
                            if(gamePanel.obj[i].collision) e.collisionOn = true;
                            if(player) index = i;
                        }
                        break;
                    case "down":
                        e.hitBox.y += e.speed;
                        if(e.hitBox.intersects(gamePanel.obj[i].hitBox)) {
                            if(gamePanel.obj[i].collision) e.collisionOn = true;
                            if(player) index = i;
                        }
                        break;
                    case "right":
                        e.hitBox.x += e.speed;
                        if(e.hitBox.intersects(gamePanel.obj[i].hitBox)) {
                            if(gamePanel.obj[i].collision) e.collisionOn = true;
                            if(player) index = i;
                        }
                        break;
                    case "left":
                        e.hitBox.x -= e.speed;
                        if(e.hitBox.intersects(gamePanel.obj[i].hitBox)) {
                            if(gamePanel.obj[i].collision) e.collisionOn = true;
                            if(player) index = i;
                        }
                        break;
                } // switch

                e.hitBox.x = e.hitBoxDefaultX;
                e.hitBox.y = e.hitBoxDefaultY;
                gamePanel.obj[i].hitBox.x = gamePanel.obj[i].hitBoxDefaultX;
                gamePanel.obj[i].hitBox.y = gamePanel.obj[i].hitBoxDefaultY;

            } // if
        } // for each

        return index;
    } // checkObject(..)

} // CollisionManager
