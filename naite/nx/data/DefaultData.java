package nx.data;

import org.bukkit.entity.Player;

import nx.core.Core;

public class DefaultData {

	public static String title = "§6[NcProperty] ";
	
	// 客戶端數值
	Player Player;      				// 玩家
	public int Point = 2;   			// 屬性點數
	
	public int Level = 1;				// 等級
	public int MaxLevel = 50;			// 最大等級  (固定制)
	
	public int Exp = 0;					// 經驗
	public int MaxExp = 100;			// 最大經驗  (等級制)
	
	public int Mana = 0;				// 魔力
	public int MaxMana = 100;			// 最大魔力  (屬性制 - 體質)
	public int RestorMana = 5;			// 魔力恢復  (屬性制 - 感知)
	
	public int Health = 100;			// 血量
	public int MaxHealth = 100;			// 最大血量  (屬性制 - 體質)
	public int RestorHealth = 5;		// 血量恢復  (屬性制 - 感知)
	
	public int Mentality = 600;			// 精力
	public int MaxVMentality = 600;		// 最大精力  (屬性制 - 體質)
	public int RestorMentality = 10;	// 精力恢復  (屬性制 - 感知)
	
	public int Vitality = 600;			// 耐力
	public int MaxVitality = 600;		// 最大耐力  (屬性制 - 體質)
	public int RestorVitality = 10;		// 耐力恢復  (屬性制 - 感知)
	
	// 基礎數值 (基礎+屬性)
	public int ATK = 5;		// 物理攻擊	(屬性制 - 力量)
	public int MAG = 5;		// 魔法攻擊	(屬性制 - 智力)
	public int DEF = 0;		// 物理防禦	(屬性制 - 敏捷)
	public int RES = 0;		// 魔法防禦	(屬性制 - 敏捷)
	public int AAR = 0;		// 物理閃避率	(屬性制 - 敏捷)
	public int SAR = 0;		// 魔法閃避率	(屬性制 - 敏捷)
	public int AKB = 3;		// 物理爆擊率	(屬性制 - 幸運)
	public int SKB = 2;		// 魔法爆擊率	(屬性制 - 幸運)
	public int AHIT = 80;	// 物理命中率	(屬性制 - 幸運)
	public int SHIT = 60;	// 魔法命中率	(屬性制 - 幸運)
	
	// 屬性數值
	public int Str = 0;		// 力量屬性  (物理攻擊)
	public int Int = 0;		// 智力屬性  (魔法攻擊)
	public int Agi = 0;		// 敏捷屬性  (物理閃避率、魔法閃避率、物理防禦、魔法防禦)
	public int Luk = 0;		// 幸運屬性  (物理爆擊率、魔法爆擊率、物理命中率、魔法命中率)
	public int Con = 0;		// 體值數性  (血量、魔力、精力、耐力)
	public int Wis = 0;		// 感知屬性  (血量恢復、魔力恢復、精力恢復、耐力恢復)
	
	// 玩家資料
	public DefaultData(Player player){
		this.Player = player;
	}
	
	// 等級管理
	public void upLevel(int exp){
		this.Exp += exp;
		this.MaxLevel = Core.plugin.getConfig().getInt("Player.Maxlevel");
		if(this.Exp >= this.MaxExp){
			this.Exp = 0;
			if(this.Level <= this.MaxLevel){
				this.MaxExp = (int)(Math.pow(this.Level*
						Core.plugin.getConfig().getInt("Player.MaxExp.Default"), 
						Core.plugin.getConfig().getDouble("Player.MaxExp.Float")));
				this.Level ++; // Level = Level + Level
				this.Point += 2; // Point = Point + 2
				this.Player.setExp(this.Exp);
				this.Player.setLevel(this.Level);
				Player.sendMessage(title + "§a恭喜您升級了!");
				Player.sendMessage(title + "§e目前等級是§f" + this.Level + "§e級");
				return;
			}else if(this.Level > this.MaxLevel){
				Player.sendMessage(title + "§c您已經達到等級上限!");
			}
		}
	}
}
