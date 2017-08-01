package nx.data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import nx.core.Core;

import java.io.File;
import java.io.IOException;

public class DefaultData{

	private static String title = "§6[NcProperty] ";

	// 客戶端數值
	Player player;  // 玩家

	private int point = 0;  // 屬性點數
	private int level = 1;  // 等級
	private int exp = 0;  // 經驗

	private int mana = 0;        // 魔力
	private int maxMana = 0;  // 最大魔力  (屬性制 - 體質)
	private int restoreMana = 0;  // 魔力恢復  (屬性制 - 感知)

	private int health = 0;      // 血量
	private int maxHealth = 0;  // 最大血量  (屬性制 - 體質)
	private int restoreHealth = 0;  // 血量恢復  (屬性制 - 感知)

	private int mentality = 0;        // 精力
	private int maxMentality = 0;    // 最大精力  (屬性制 - 體質)
	private int restoreMentality = 0;  // 精力恢復  (屬性制 - 感知)

	private int vitality = 0;        // 耐力
	private int maxVitality = 0;    // 最大耐力  (屬性制 - 體質)
	private int restoreVitality = 0;  // 耐力恢復  (屬性制 - 感知)

	// 基礎數值 (基礎+屬性)
	private int atk = 0;        // 物理攻擊		(屬性制 - 力量)
	private int mag = 0;        // 魔法攻擊		(屬性制 - 智力)
	private int def = 0;        // 物理防禦		(屬性制 - 敏捷)
	private int res = 0;        // 魔法防禦		(屬性制 - 敏捷)
	private double aar = 0;        // 物理閃避率	(屬性制 - 敏捷)
	private double sar = 0;        // 魔法閃避率	(屬性制 - 敏捷)
	private double akb = 0;        // 物理爆擊率	(屬性制 - 幸運)
	private double skb = 0;        // 魔法爆擊率	(屬性制 - 幸運)
	private double ahit = 0;    // 物理命中率	(屬性制 - 幸運)
	private double shit = 0;    // 魔法命中率	(屬性制 - 幸運)

	// 屬性數值
	private int str = 0;        // 力量屬性  (物理攻擊)
	private int inte = 0;        // 智力屬性  (魔法攻擊)
	private int agi = 0;        // 敏捷屬性  (物理閃避率、魔法閃避率、物理防禦、魔法防禦)
	private int luk = 0;        // 幸運屬性  (物理爆擊率、魔法爆擊率、物理命中率、魔法命中率)
	private int con = 0;        // 體質屬性  (血量、魔力、精力、耐力)
	private int wis = 0;        // 感知屬性  (血量恢復、魔力恢復、精力恢復、耐力恢復)

	//檔案
	private File file = null;
	private YamlConfiguration yaml = null;

	// 玩家資料
	public DefaultData(Player player){
		this.player = player;
		player.setHealthScaled(true);
		loadFile();
	}

	//屬性與能力值讀取與變更

	//檔案讀取
	private void loadFile(){
		file = new File(Core.plugin.getDataFolder() + File.separator + "PlayerData" + File.separator + player.getUniqueId() + ".yml");

		if(!file.exists()){
			try{
				file.createNewFile();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		yaml = YamlConfiguration.loadConfiguration(file);

		//讀取資料

		//基本資料
		if(yaml.getString("point", null) == null){
			yaml.set("point", 0);
		}
		point = yaml.getInt("point");

		if(yaml.getString("level", null) == null){
			yaml.set("level", 1);
		}
		level = yaml.getInt("level");

		if(yaml.getString("exp", null) == null){
			yaml.set("exp", 0);
		}
		exp = yaml.getInt("exp");

		//屬性
		if(yaml.getString("str", null) == null){
			yaml.set("str", 0);
		}
		str = yaml.getInt("str");
		computeStr();

		if(yaml.getString("int", null) == null){
			yaml.set("int", 0);
		}
		inte = yaml.getInt("int");
		computeInt();

		if(yaml.getString("agi", null) == null){
			yaml.set("agi", 0);
		}
		agi = yaml.getInt("agi");
		computeAgi();

		if(yaml.getString("luk", null) == null){
			yaml.set("luk", 0);
		}
		luk = yaml.getInt("luk");
		computeLuk();

		if(yaml.getString("con", null) == null){
			yaml.set("con", 0);
		}
		con = yaml.getInt("con");
		computeCon();

		if(yaml.getString("wis", null) == null){
			yaml.set("wis", 0);
		}
		wis = yaml.getInt("wis");
		computeWis();

		//基本數值
		if(yaml.getString("mana", null) == null){
			yaml.set("mana", getMaxMana());
		}
		mana = yaml.getInt("mana") > getMaxMana() ? getMaxMana() : yaml.getInt("mana");

		if(yaml.getString("health", null) == null){
			yaml.set("health", getMaxHealth());
		}
		health = yaml.getInt("health") > getMaxHealth() ? getMaxHealth() : yaml.getInt("health");

		if(yaml.getString("mentality", null) == null){
			yaml.set("mentality", getMaxMentality());
		}
		mentality = yaml.getInt("mentality") > getMaxMentality() ? getMaxMentality() : yaml.getInt("mentality");

		if(yaml.getString("vitality", null) == null){
			yaml.set("vitality", getMaxVitality());
		}
		vitality = yaml.getInt("vitality") > getMaxVitality() ? getMaxVitality() : yaml.getInt("vitality");

		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	//計算屬性相關的能力值與基本數值

	private void computeStr(){
		atk = Settings.getDefaultAtk() + getStr(); //物理攻擊 = 預設 + 力量*1
	}

	private void computeInt(){
		mag = Settings.getDefaultMag() + getInt() * 2; //魔法攻擊 = 預設 + 智力*2
	}

	private void computeAgi(){
		def = Settings.getDefaultDef() + getAgi() * 2; //物理防禦 = 預設 + 敏捷*2
		res = Settings.getDefaultRes() + getAgi(); //魔法防禦 = 預設 + 敏捷*1
		aar = Settings.getDefaultAar() + getAgi() * 0.03; //物理閃避 = 預設 + 敏捷*0.03
		sar = Settings.getDefaultSar() + getAgi() * 0.02; //魔法閃避 = 預設 + 敏捷*0.02
	}

	private void computeLuk(){
		akb = Settings.getDefaultAkb() + getLuk() * 0.05; //物理爆擊 = 預設 + 幸運*0.05
		skb = Settings.getDefaultSkb() + getLuk() * 0.03; //魔法爆擊 = 預設 + 幸運*0.03
		ahit = Settings.getDefaultAhit() + getLuk() * 0.02; //物理命中 = 預設 + 幸運*0.02
		shit = Settings.getDefaultShit() + getLuk() * 0.01; //魔法命中 = 預設 + 幸運*0.01
	}

	private void computeCon(){
		maxHealth = Settings.getDefaultMaxHealth() + getCon() * 10; //最大血量 = 預設 + 體質*10
		maxMana = Settings.getDefaultMaxMana() + getCon() * 5; //最大魔力 = 預設 + 體質*5
		maxMentality = Settings.getDefaultMaxMentality() + getCon(); //最大精力 = 預設 + 體質*1
		maxVitality = Settings.getDefaultMaxVitality() + getCon(); //最大耐力 = 預設 + 體質*1
	}

	private void computeWis(){
		restoreHealth = Settings.getDefaultRestoreHealth() + getWis() * 5; //血量恢復 = 預設 + 感知*5
		restoreMana = Settings.getDefaultRestoreMana() + getWis() * 5; //魔力恢復 = 預設 + 感知*5
		restoreMentality = Settings.getDefaultRestoreMentality() + getWis(); //精力恢復 = 預設 + 感知*1
		restoreVitality = Settings.getDefaultRestoreVitality() + getWis(); //耐力恢復 = 預設 + 感知*1
	}

	//設定(並且更新該屬性相關的能力值與變更檔案內容)與取得屬性

	//STR
	public void setStr(int value){
		str = value;
		computeStr();
		yaml.set("str", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getStr(){
		return str;
	}

	public void addStr(int value){
		setStr(getStr() + value);
	}

	//INT
	public void setInt(int value){
		inte = value;
		computeInt();
		yaml.set("int", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getInt(){
		return inte;
	}

	public void addInt(int value){
		setInt(getInt() + value);
	}

	//AGI
	public void setAgi(int value){
		agi = value;
		computeAgi();
		yaml.set("agi", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getAgi(){
		return agi;
	}

	public void addAgi(int value){
		setAgi(getAgi() + value);
	}

	//LUK
	public void setLuk(int value){
		luk = value;
		computeLuk();
		yaml.set("luk", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getLuk(){
		return luk;
	}

	public void addLuk(int value){
		setLuk(getLuk() + value);
	}

	//CON
	public void setCon(int value){
		con = value;
		computeCon();
		yaml.set("con", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getCon(){
		return con;
	}

	public void addCon(int value){
		setCon(getCon() + value);
	}

	//WIS
	public void setWis(int value){
		wis = value;
		computeWis();
		yaml.set("wis", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getWis(){
		return wis;
	}

	public void addWis(int value){
		setWis(getWis() + value);
	}

	//技能點設定(並且變更檔案內容)與取得

	public void setPoint(int value){
		point = value;

		yaml.set("point", value);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getPoint(){
		return point;
	}

	public void addPoint(int value){
		setPoint(getPoint() + value);
	}

	// 等級管理

	private void changeMinecraftExp(){ //修改原版經驗條
		//經驗條修改
		player.setLevel(level);

		int max;
		if(level <= 15){
			max = 2 * level + 7;
		}
		else if(level <= 30){
			max = 5 * level - 38;
		}
		else{
			max = 9 * level - 158;
		}

		player.setExp(max * (level / Settings.getMaxExp(level)));
	}

	public void addLevel(int lvl){ //提升等級(並且發送訊息及增加技能點)
		setLevel(getLevel() + lvl); //提升等級
		addPoint(2 * lvl); //point + 2

		player.sendMessage(title + "§a恭喜您升級了!");
		player.sendMessage(title + "§e目前等級是§f" + level + "§e級");
	}

	public void setLevel(int lvl){ //設定等級並變更檔案內容
		level = lvl;
		yaml.set("level", lvl);
		try{
			yaml.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public int getLevel(){
		return level;
	}

	public void addExp(int e){ //增加經驗值(若超過最大經驗會升級)
		setExp(getExp() + e);
	}

	public void setExp(int e){ //設定經驗值(若超過最大經驗會升級)(並變更檔案內容)
		exp = e;

		int upLvl = 0; //提升的等級
		for(; ; ){
			if(exp >= Settings.getMaxExp(level) && level < Settings.getMaxLevel()){ //達到可升級經驗且未達最高等級
				exp -= Settings.getMaxExp(level); //扣除經驗值
				upLvl++;
			}
			else{
				break;
			}
		}
		if(upLvl > 0){
			addLevel(upLvl);
		}

		changeMinecraftExp();

		yaml.set("exp", getExp());
		try{
			yaml.save(file);
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	public int getExp(){
		return exp;
	}

	//基本數值控制

	//mana
	public int getMana(){
		return mana;
	}

	public int getMaxMana(){
		return maxMana;
	}

	public int getRestoreMana(){
		return restoreMana;
	}

	public void setMana(int value){
		mana = value > getMaxMana() ? getMaxMana() : value;
	}

	public void removeMana(int value){
		setMana(getMana() - value);
	}

	public void addMana(int value){
		setMana(getMana() + value);
	}

	//health
	public int getHealth(){
		return health;
	}

	public int getMaxHealth(){
		return maxHealth;
	}

	public int getRestoreHealth(){
		return restoreHealth;
	}

	public void setHealth(int value){
		health = value > getMaxHealth() ? getMaxHealth() : value;
		player.setHealthScale(getHealth());
	}

	public void removeHealth(int value){
		setHealth(getHealth() - value);
	}

	public void addHealth(int value){
		setHealth(getHealth() + value);
	}

	//mentality
	public int getMentality(){
		return mentality;
	}

	public int getMaxMentality(){
		return maxMentality;
	}

	public int getRestoreMentality(){
		return restoreMentality;
	}

	public void setMentality(int value){
		mentality = value > getMaxMentality() ? getMaxMentality() : value;
	}

	public void removeMentality(int value){
		setMentality(getMentality() - value);
	}

	public void addMentality(int value){
		setMentality(getMentality() + value);
	}

	//vitality
	public int getVitality(){
		return vitality;
	}

	public int getMaxVitality(){
		return maxVitality;
	}

	public int getRestoreVitality(){
		return restoreVitality;
	}

	public void setVitality(int value){
		vitality = value > getMaxVitality() ? getMaxVitality() : value;
	}

	public void removeVitality(int value){
		setVitality(getVitality() - value);
	}

	public void addVitality(int value){
		setVitality(getVitality() + value);
	}

	//取得能力值

	public int getAtk(){
		return atk;
	}

	public int getMag(){
		return mag;
	}

	public int getDef(){
		return def;
	}

	public int getRes(){
		return res;
	}

	public double getAar(){
		return aar;
	}

	public double getSar(){
		return sar;
	}

	public double getAkb(){
		return akb;
	}

	public double getSkb(){
		return skb;
	}

	public double getAhit(){
		return ahit;
	}

	public double getShit(){
		return shit;
	}
}
