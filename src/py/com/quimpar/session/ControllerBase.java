package py.com.quimpar.session;

/**
 * 
 * @author bsandoval
 * 
 */
public abstract class ControllerBase {
	
	private String initVar = null;
	private String action = null;
	private String descAction = null;
	private int pagina = 0;
	
	public abstract void initInfo();

	public void initAction() {
		this.initAction(null, null);
	}

	public void initAction(String action, String desc) {
		this.setAction(action);
		this.setDescAction(desc);
	}

	public String getDescAction() {
		return descAction;
	}

	public void setDescAction(String descAction) {
		this.descAction = descAction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String executeBack() {
		String res = this.getAction();
		this.initAction();
		return res;
	}

	public String getInitVar() {
		return initVar;
	}

	public void setInitVar(String initVar) {
		this.initVar = initVar;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
}
